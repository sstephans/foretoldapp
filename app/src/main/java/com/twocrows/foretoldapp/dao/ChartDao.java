package com.twocrows.foretoldapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.twocrows.foretoldapp.entity.Chart;

import java.util.List;

@Dao
public interface ChartDao {


    @Insert
    void insert(Chart chart);

    @Delete
    void delete(Chart user);

    @Query("DELETE FROM chart_table")
    void deleteAll();

    @Update
    void update(Chart chart);

    // has to do with relations which I still need to learn about TODO
    //@Transaction
    //@Query("SELECT * FROM chart_table")
    //public List<ChartAndDateTime> getChartsAndDateTimes();

    @Query("SELECT * FROM chart_table ORDER BY name ASC")
    LiveData<List<Chart>> getAllCharts();

    @Query("SELECT * FROM chart_table WHERE id IN (:userIds)")
    LiveData<List<Chart>> loadAllByIds(int[] userIds);
    // returning this like LiveData<List<Chart>> means that as soon as there are any changes
    // in the chart_table, the list/objects will be updated and the app will be notified

    @Query("SELECT * FROM chart_table WHERE name LIKE :userName LIMIT 1")
    LiveData<Chart> findByName(String userName);

}
