package pro.butovanton.countries2.Net;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.ResponseBody;
import pro.butovanton.countries2.Repo.Model;
import retrofit2.Response;

public class Api {

    private Context context;
    private NetworkService networkService;
    private JSONPlaceHolderApi jsonPlaceHolderApi;

   public Api (Context context) {
       this.context = context;
        networkService = NetworkService.getInstance();
        jsonPlaceHolderApi = networkService.getJSONApi();
    }


    public List<Model> getAll() throws IOException {
        List<Model> countrise = null;
        countrise = jsonPlaceHolderApi.getAllCountries().execute().body();
        return countrise;
    }

    public void downloadFlags(List<Model> countries) throws IOException {
        for (Model model : countries) {
            String url = model.getflag();
            Response<ResponseBody> responseBody = downloadFlag(url);
            String patch = writeResponseBodyToDisk(responseBody, getFilename(url));
            model.setFlagPatch(patch);
            Log.d("DEBUG", patch);
        }
    }

    Response<ResponseBody> downloadFlag(String patch) {
        Response<ResponseBody> responseBody = null;
        try {
            responseBody = jsonPlaceHolderApi.downloadFlag(patch).execute();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("DEBUG","download failure " + e);
        }
        return responseBody;
    }

    private String writeResponseBodyToDisk(Response<ResponseBody> body, String filename) throws IOException {
            File futureStudioIconFile = new File(context.getFilesDir() + File.separator + filename);
            String res = futureStudioIconFile.getAbsolutePath();
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.body().contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.body().byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("DEBUG", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return res;
            } catch (IOException e) {
                return "";
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }

    String getFilename(String patch) {
        Uri uri= Uri.parse(patch);
        return new File(uri.getPath()).getName();
    }

}
