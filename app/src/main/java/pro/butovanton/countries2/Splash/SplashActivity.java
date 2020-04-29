package pro.butovanton.countries2.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import pro.butovanton.countries2.MainContract;
import pro.butovanton.countries2.R;
import pro.butovanton.countries2.View.Activity2;

public class SplashActivity extends AppCompatActivity implements MainContract.View {

    private Presenter presenter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter( this );
    }

    @Override
    public void LoadOk() {
    progressBar = findViewById(R.id.progressBar);
    progressBar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(SplashActivity.this, Activity2.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void LoadFailure() {
        Toast toast = Toast.makeText(getApplicationContext(), "Отсутсвует интернет.", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
