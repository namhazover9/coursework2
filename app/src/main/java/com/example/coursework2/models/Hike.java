package com.example.coursework2.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "hikes")
public class Hike {
    @PrimaryKey(autoGenerate = true)
    public long hike_id;
    public String hike_name;
    public String location;
    public String date;
    public String parking;
    public float hike_length;
    public String hike_level;
    public String description;
}
