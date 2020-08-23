package com.twocrows.foretoldapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.twocrows.foretoldapp.dao.ChartDao;
import com.twocrows.foretoldapp.entity.Chart;

@Database(entities = {Chart.class}, version = 1, exportSchema = false)
public abstract class ForetoldDatabase extends RoomDatabase {

    // singleton instance of the database
    private static ForetoldDatabase instance;

    public abstract ChartDao chartDao();

    // synchronized means it can only be called once so you don't accidentally make more than one instance in your app
    public static synchronized ForetoldDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ForetoldDatabase.class, "foretold_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }

    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ChartDao chartDao;

        public PopulateDbAsyncTask(ForetoldDatabase db) {
            chartDao = db.chartDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            chartDao.insert(new Chart("Sammi", "St. Charles, IL",11, 11, 1986, 05, 37));
            return null;
        }
    }
}
