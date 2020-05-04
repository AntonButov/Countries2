package pro.butovanton.countries2.Splash;

import pro.butovanton.countries2.MainContract;
import pro.butovanton.countries2.Repo.Repo;

public class Presenter implements MainContract.Presenter, MainContract.Model.OnFinishedListener {

    private MainContract.View splashActivity;

    public Presenter(MainContract.View splashActivity) {

        this.splashActivity = splashActivity;
        Repo.getInstance().startLoad( this);
    }

    @Override
    public void Finished() {
    splashActivity.LoadOk();
    }

    @Override
    public void Failure() {
    splashActivity.LoadFailure();
    }

    @Override
    public void onDestroy() {
        this.splashActivity = null;
    }
}
