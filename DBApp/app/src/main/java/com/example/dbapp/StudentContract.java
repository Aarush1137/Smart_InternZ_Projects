package com.example.dbapp;

import android.provider.BaseColumns;

public final class StudentContract {

    private StudentContract() {}

    public static class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_ROLL_NO = "roll_no";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_MARKS = "marks";
    }
}
