package com.example.quiz_52.guns;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GunDao {

    @Query("SELECT * FROM Gun")
    List<Gun> getAllGuns();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Gun... guns);

    @Delete
    void delete(Gun gun);

    @Query("DELETE FROM Gun")
    void deleteAll();

    @Query("SELECT * FROM Gun C WHERE C.GUN_NAME LIKE :name AND C.CALIBER > :caliber LIMIT 1")
    Gun getByNameAndCaliber(String name, Float caliber);

    @Query("SELECT * FROM Gun C WHERE C.GUN_NAME LIKE :name")
    List<Gun> getByName(String name);

}
