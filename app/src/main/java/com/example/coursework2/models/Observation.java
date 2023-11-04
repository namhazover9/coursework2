package com.example.coursework2.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "observations",
        foreignKeys = @ForeignKey(entity = Hike.class,
                parentColumns = "hike_id",
                childColumns = "hike_id",
                onDelete = ForeignKey.CASCADE))
public class Observation {
    @PrimaryKey(autoGenerate = true)
    public long observation_id;
    public long hike_id;
    public String time;
    public String description;

}
