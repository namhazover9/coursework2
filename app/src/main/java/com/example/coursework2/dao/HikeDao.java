package com.example.coursework2.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.coursework2.models.Hike;

import java.util.List;

@Dao
public interface HikeDao {
    @Insert
    long insertHike(Hike hike);

    @Query("SELECT * FROM hikes ORDER BY hike_name")
    List<Hike> getAllHikes();

    @Update
    void updateHike(Hike hike);

    @Delete
    void deleteHike(Hike hike);

    @Query("DELETE FROM hikes")
    void deleteAllHikes();

    @Query("SELECT * FROM hikes WHERE hike_id = :hikeId")
    Hike getHikeById(long hikeId);

    @Query("SELECT * FROM hikes WHERE hike_name LIKE '%' || :name || '%'")
    List<Hike> searchHikesByName(String name);
}
