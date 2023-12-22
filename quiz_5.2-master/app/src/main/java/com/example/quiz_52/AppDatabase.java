package com.example.quiz_52;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.quiz_52.guns.Gun;
import com.example.quiz_52.guns.GunDao;

@Database(entities = {Gun.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    abstract GunDao getGunDao();
}