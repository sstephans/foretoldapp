package com.twocrows.foretoldapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.twocrows.foretoldapp.adapter.ChartAdapter;
import com.twocrows.foretoldapp.entity.Chart;
import com.twocrows.foretoldapp.viewmodel.ChartViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ChartViewModel chartViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ChartAdapter adapter = new ChartAdapter();
        recyclerView.setAdapter(adapter);

        //chartViewModel = ViewModelProviders.of(this).get(ChartViewModel.class);
        chartViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ChartViewModel.class);
        chartViewModel.getAllCharts().observe(this, new Observer<List<Chart>>() {
            @Override
            public void onChanged(List<Chart> charts) {
                adapter.setCharts(charts);
            }
        });
    }
}