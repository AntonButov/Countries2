package pro.butovanton.countries2.Net;

import java.util.List;

import okhttp3.ResponseBody;
import pro.butovanton.countries2.Repo.Model;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface JSONPlaceHolderApi {
    @GET("rest/v2/all")
    public Call<List<Model>> getAllCountries();

    @GET("")
    Call<ResponseBody> downloadFlag(@Url String fileUrl);
}
