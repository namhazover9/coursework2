package com.example.coursework2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.coursework2.models.Observation;

import java.util.List;

@Dao
public interface ObservationDao {
    @Insert
    long insertObservation(Observation observation);

    @Query("SELECT * FROM observations WHERE hike_id = :hike_id")
    List<Observation> getObservationsForHike(long hike_id);

    @Delete
    void deleteObservation(Observation observation);

    @Update
    void updateObservation(Observation observation);


}