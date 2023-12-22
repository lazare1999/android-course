package com.example.quiz_51.guns;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GunDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB_NAME";
    private static final int VERSION = 1;

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + GunContract.TABLE_NAME + " ("
            + GunContract.ID + " INTEGER PRIMARY KEY, "
            + GunContract.GUN_NAME + " TEXT, "
            + GunContract.CALIBER + " REAL, "
            + GunContract.YEAR_OF_RELEASE + " INTEGER NOT NULL)";

    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + GunContract.TABLE_NAME;

    public GunDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    public void insert(String name, int year, float caliber) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(GunContract.GUN_NAME, name);
        contentValues.put(GunContract.YEAR_OF_RELEASE, year);
        contentValues.put(GunContract.CALIBER, caliber);

        getWritableDatabase().insert(GunContract.TABLE_NAME, null, contentValues);

    }

    public int update(long id, String newGunName) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(GunContract.GUN_NAME, newGunName);

        String where = GunContract.ID + " = ?";
        String[] args = new String[]{
                String.valueOf(id)
        };

        return getWritableDatabase().update(
                GunContract.TABLE_NAME,
                contentValues,
                where,
                args
        );

    }

    public int delete(long gunId) {

        String where = GunContract.ID + " = ?";
        String[] args = new String[]{
                String.valueOf(gunId)
        };

        return getWritableDatabase().delete(GunContract.TABLE_NAME, where, args);

    }

    public int deleteAll() {
        return getWritableDatabase().delete(GunContract.TABLE_NAME, null, null);
    }

    @SuppressLint("Range")
    public List<GunModel> select(String name) {

        String[] projection = new String[]{
                GunContract.GUN_NAME, GunContract.YEAR_OF_RELEASE, GunContract.CALIBER
        };

        String where = GunContract.GUN_NAME + " LIKE ?";
        String[] args = new String[]{
                "%" + name + "%"
        };

        String ordering = GunContract.YEAR_OF_RELEASE + " DESC";

        @SuppressLint("Recycle") Cursor cursor = getReadableDatabase().query(
                GunContract.TABLE_NAME,
                projection,
                where,
                args,
                null,
                null,
                ordering
        );

        List<GunModel> guns = new ArrayList<>();

        while (cursor.moveToNext()) {
            guns.add(new GunModel(
                    cursor.getString(cursor.getColumnIndex(GunContract.GUN_NAME)),
                    cursor.getFloat(cursor.getColumnIndex(GunContract.CALIBER)),
                    cursor.getInt(cursor.getColumnIndex(GunContract.YEAR_OF_RELEASE))
            ));
        }

        return guns;

    }
}