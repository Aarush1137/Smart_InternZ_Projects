package com.example.dbapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StudentDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Define the SQL statement to create the student table
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + StudentContract.StudentEntry.TABLE_NAME + " (" +
                    StudentContract.StudentEntry._ID + " INTEGER PRIMARY KEY," +
                    StudentContract.StudentEntry.COLUMN_ROLL_NO + " TEXT," +
                    StudentContract.StudentEntry.COLUMN_NAME + " TEXT," +
                    StudentContract.StudentEntry.COLUMN_MARKS + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the student table
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade policy for the database (if needed)
        // You can handle database schema changes here
    }
}
