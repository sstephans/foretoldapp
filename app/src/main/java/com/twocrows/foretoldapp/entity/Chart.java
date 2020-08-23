package com.twocrows.foretoldapp.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chart_table")
public class Chart {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String location;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;

    //@Ignore will omit from the table
    //@ColumnInfo(name = "column_name") to override the default name

    public Chart(String name, String location, int day, int month, int year, int hour, int minute) {
        this.name = name;
        this.location = location;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}

