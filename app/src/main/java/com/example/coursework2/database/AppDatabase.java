package com.example.coursework2.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.coursework2.dao.HikeDao;
import com.example.coursework2.dao.ObservationDao;
import com.example.coursework2.models.Hike;
import com.example.coursework2.models.Observation;

@Database(entities = {Hike.class, Observation.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HikeDao hikeDao();
    public abstract ObservationDao observationDao();
}
