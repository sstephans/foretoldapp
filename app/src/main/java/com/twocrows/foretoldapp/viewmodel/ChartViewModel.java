package com.twocrows.foretoldapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.twocrows.foretoldapp.ForetoldRepository;
import com.twocrows.foretoldapp.entity.Chart;

import java.util.List;

public class ChartViewModel extends AndroidViewModel {

    private ForetoldRepository repository;
    private LiveData<List<Chart>> allCharts;

    public ChartViewModel(@NonNull Application application) {
        super(application);
        repository = new ForetoldRepository(application);
        allCharts = repository.getAllCharts();
    }

    public void insert(Chart chart) {
        repository.insert(chart);
    }

    public void update(Chart chart) {
        repository.update(chart);
    }

    public void delete(Chart chart) {
        repository.delete(chart);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<Chart>> getAllCharts() {
        return allCharts;
    }
}
