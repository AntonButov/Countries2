package pro.butovanton.countries2.Repo;

import android.app.Application;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import pro.butovanton.countries2.MainContract;
import pro.butovanton.countries2.Net.Api;
import pro.butovanton.countries2.Room.cDao;
import pro.butovanton.countries2.Room.cRoomDatabase;

public class Repo extends Application implements MainContract.Model {

    private static Repo Instance;
    private OnFinishedListener onFinish;
    private List<Model> countries;
    private cDao dao;

    public Repo() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
        cRoomDatabase db = cRoomDatabase.getDatabase( this );
        dao = db.cdao();
    }

    public static Repo getInstance() {
        return Instance;
}

    @Override
    public void startLoad(OnFinishedListener onFinishedListener) {
    if (onFinish ==  null)
        new startLoadModel().execute();
    onFinish = onFinishedListener;
    }

    private class startLoadModel extends AsyncTask<Void, Void, Boolean>  {
            @Override
            protected Boolean doInBackground(Void... params) {
                countries = roomGet();
                if (countries.size() == 0) {
                    try {
                        Api api = new Api(getApplicationContext());
                        countries = api.getAll();
                        api.downloadFlags(countries);
                        if (testFlagsOk(countries)) {
                            saveToRoom(countries);
                            return true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    countries.clear();
                    return false;
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean testOk) {
                super.onPostExecute(testOk);
               // roomDelAll();
                if (testOk) onFinish.Finished();
                else onFinish.Failure();
            }
        }

    public List<Model> roomGet() {
        return dao.getAll();
    }

    public void saveToRoom(List<Model> countries) {
        for (Model model : countries)
            dao.insert(model);
    }

   public void roomDelAll() {
        dao.deleteAll();
   }

    private boolean testFlagsOk(List<Model> countries) {
        for (Model model : countries)
            if (model.getflag() == null) return false;
    return true;
    }

    public List<Model>getModel() {
        return countries;
    }
}
