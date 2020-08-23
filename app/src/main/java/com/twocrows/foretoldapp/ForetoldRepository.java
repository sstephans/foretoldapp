package com.twocrows.foretoldapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.twocrows.foretoldapp.dao.ChartDao;
import com.twocrows.foretoldapp.entity.Chart;

import java.util.List;

public class ForetoldRepository {
    private ChartDao chartDao;
    private LiveData<List<Chart>> allCharts;

    public ForetoldRepository(Application application) {
        // application is a subclass of context, so we can use it to get the database
        ForetoldDatabase database = ForetoldDatabase.getInstance(application);
        chartDao = database.chartDao();
        allCharts = chartDao.getAllCharts();
    }

    public void insert(Chart chart) {
        new InsertChartAsyncTask(chartDao).execute(chart);
    }

    public void update(Chart chart) {
        new UpdateChartAsyncTask(chartDao).execute(chart);
    }

    public void delete(Chart chart) {
        new DeleteChartAsyncTask(chartDao).execute(chart);
    }

    public void deleteAll() {
        new DeleteAllChartsAsyncTask(chartDao).execute();
    }

    public LiveData<List<Chart>> getAllCharts() {
        return allCharts;
    }

    private static class InsertChartAsyncTask extends AsyncTask<Chart, Void, Void> {
        private ChartDao chartDao;
        private InsertChartAsyncTask(ChartDao chartDao) {
            this.chartDao = chartDao;
        }
        @Override
        protected Void doInBackground(Chart... charts) {
            chartDao.insert(charts[0]);
            return null;
        }
    }
    private static class UpdateChartAsyncTask extends AsyncTask<Chart, Void, Void> {
        private ChartDao chartDao;
        private UpdateChartAsyncTask(ChartDao chartDao) {
            this.chartDao = chartDao;
        }
        @Override
        protected Void doInBackground(Chart... charts) {
            chartDao.update(charts[0]);
            return null;
        }
    }
    private static class DeleteChartAsyncTask extends AsyncTask<Chart, Void, Void> {
        private ChartDao chartDao;
        private DeleteChartAsyncTask(ChartDao chartDao) {
            this.chartDao = chartDao;
        }
        @Override
        protected Void doInBackground(Chart... charts) {
            chartDao.delete(charts[0]);
            return null;
        }
    }
    private static class DeleteAllChartsAsyncTask extends AsyncTask<Chart, Void, Void> {
        private ChartDao chartDao;
        private DeleteAllChartsAsyncTask(ChartDao chartDao) {
            this.chartDao = chartDao;
        }
        @Override
        protected Void doInBackground(Chart... charts) {
            chartDao.deleteAll();
            return null;
        }
    }
}
