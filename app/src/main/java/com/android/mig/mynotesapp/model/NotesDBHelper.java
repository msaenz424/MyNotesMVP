package com.android.mig.mynotesapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class NotesDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "NotesDB.db";
    private static final int DATABASE_VERSION = 1;

    NotesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String  CREATE_TABLE = "CREATE TABLE " + NotesContract.NotesEntry.TABLE_NOTES + " (" +
                NotesContract.NotesEntry._ID + " INTEGER PRIMARY KEY, " +
                NotesContract.NotesEntry.COLUMN_NOTE + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
