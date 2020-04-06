package pro.butovanton.countries2.View;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pro.butovanton.countries2.R;
import pro.butovanton.countries2.Repo.Model;
import pro.butovanton.countries2.Repo.Repo;


public class Activity2 extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        recyclerView = findViewById(R.id.reciclerView);
        List<Model> countrieList =  Repo.getInstance().getModel();
        final cRecyclerAdapter adapter = new cRecyclerAdapter(this,  countrieList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }
}










