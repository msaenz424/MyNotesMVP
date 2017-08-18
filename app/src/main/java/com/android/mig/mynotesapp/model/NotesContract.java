package com.android.mig.mynotesapp.model;

import android.net.Uri;
import android.provider.BaseColumns;

public class NotesContract {

    public static final String AUTHORITY = "com.android.mig.mynotesapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_NOTES = "notes";


    public static final class NotesEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_NOTES).build();

        /************** NOTES TABLE *****************/
        public static final String TABLE_NOTES = "notes";
        // "_ID" column is automatically created by BaseColumns
        public static final String COLUMN_NOTE = "note";
    }
}
