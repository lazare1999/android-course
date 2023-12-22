package com.example.test.todo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ToDoDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB_NAME";
    private static final int VERSION = 2;

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + ToDoContract.TABLE_NAME + " ("
            + ToDoContract.ID + " INTEGER PRIMARY KEY, "
            + ToDoContract.TODO_TITLE + " TEXT, "
            + ToDoContract.TODO_DESCRIPTION + " TEXT)";

    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + ToDoContract.TABLE_NAME;

    public ToDoDbHelper(@Nullable Context context) {
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

    public void insert(String title, String description) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ToDoContract.TODO_TITLE, title);
        contentValues.put(ToDoContract.TODO_DESCRIPTION, description);

        getWritableDatabase().insert(ToDoContract.TABLE_NAME, null, contentValues);

    }

    public int update(long id, String title) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ToDoContract.TODO_TITLE, title);

        String where = ToDoContract.ID + " = ?";
        String[] args = new String[]{
                String.valueOf(id)
        };

        return getWritableDatabase().update(
                ToDoContract.TABLE_NAME,
                contentValues,
                where,
                args
        );

    }

    public int delete(long gunId) {

        String where = ToDoContract.ID + " = ?";
        String[] args = new String[]{
                String.valueOf(gunId)
        };

        return getWritableDatabase().delete(ToDoContract.TABLE_NAME, where, args);

    }

    public int deleteAll() {
        return getWritableDatabase().delete(ToDoContract.TABLE_NAME, null, null);
    }

    @SuppressLint("Range")
    public List<ToDoModel> select(String name) {

        String[] projection = new String[]{
                ToDoContract.TODO_TITLE, ToDoContract.TODO_DESCRIPTION
        };

        String where = ToDoContract.TODO_TITLE + " LIKE ?";
        String[] args = new String[]{
                "%" + name + "%"
        };

        String ordering = ToDoContract.ID + " DESC";

        @SuppressLint("Recycle") Cursor cursor = getReadableDatabase().query(
                ToDoContract.TABLE_NAME,
                projection,
                where,
                args,
                null,
                null,
                ordering
        );

        List<ToDoModel> todo = new ArrayList<>();

        while (cursor.moveToNext()) {
            todo.add(new ToDoModel(
                    cursor.getString(cursor.getColumnIndex(ToDoContract.TODO_TITLE)),
                    cursor.getString(cursor.getColumnIndex(ToDoContract.TODO_DESCRIPTION))
            ));
        }

        return todo;

    }
}