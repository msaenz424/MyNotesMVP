package com.android.mig.mynotesapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import static android.provider.BaseColumns._ID;
import static com.android.mig.mynotesapp.model.NotesContract.NotesEntry.COLUMN_NOTE;
import static com.android.mig.mynotesapp.model.NotesContract.NotesEntry.CONTENT_URI;

public class NotesInteractorImpl implements NotesInteractor {

    private Context mContext;

    public NotesInteractorImpl(Context context){
        this.mContext = context;
    }

    @Override
    public void saveNote(OnTransactionFinishedListener onTransactionFinishedListener, String note) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(COLUMN_NOTE, note);
        mContext.getContentResolver().insert(CONTENT_URI, contentValue);
        onTransactionFinishedListener.onSuccess();
    }

    @Override
    public void deleteNote(OnTransactionFinishedListener onSaveFinishedListener, String id) {
        Uri uri = CONTENT_URI;
        uri = uri.buildUpon().appendPath(id).build();
        mContext.getContentResolver().delete(uri, _ID, new String[]{id});
        onSaveFinishedListener.onSuccess();
    }

    @Override
    public Cursor getNotes() {
        Cursor notesCursor = mContext.getContentResolver().query(NotesContract.NotesEntry.CONTENT_URI, new String[]{_ID, COLUMN_NOTE}, null, null, null);

        return notesCursor;
    }
}
