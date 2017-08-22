package com.android.mig.mynotesapp.model;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static android.provider.BaseColumns._ID;
import static com.android.mig.mynotesapp.model.NotesContract.NotesEntry.TABLE_NOTES;

public class NotesContentProvider extends ContentProvider {

    public static final int NOTES = 100;
    public static final int NOTES_WITH_ID = 101;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private NotesDBHelper mNotesDBHelper;

    /**
     Initialize a new matcher object without any matches,
     then use .addURI(String authority, String path, int match) to add matches
     */
    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(NotesContract.AUTHORITY, NotesContract.PATH_NOTES, NOTES);
        uriMatcher.addURI(NotesContract.AUTHORITY, NotesContract.PATH_NOTES + "/#", NOTES_WITH_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        mNotesDBHelper = new NotesDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = mNotesDBHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor retCursor;

        switch (match) {
            case NOTES:
                retCursor =  db.query(TABLE_NOTES,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mNotesDBHelper.getWritableDatabase();
        Uri returnUri = null;

        switch (sUriMatcher.match(uri)){
            case NOTES:
                long id = db.insert(TABLE_NOTES, null, contentValues);
                if (id != -1 ){
                    returnUri = ContentUris.withAppendedId(NotesContract.NotesEntry.CONTENT_URI, id);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mNotesDBHelper.getWritableDatabase();
        int rowsDeleted;
        switch (sUriMatcher.match(uri)){
            case NOTES_WITH_ID:
                String id = uri.getPathSegments().get(1);   // get(1) gets the segment next to "notes"
                rowsDeleted = db.delete(TABLE_NOTES, _ID + "=?", new String[]{id});
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (rowsDeleted != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
