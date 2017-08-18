package com.android.mig.mynotesapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import static com.android.mig.mynotesapp.model.NotesContract.NotesEntry.COLUMN_NOTE;
import static com.android.mig.mynotesapp.model.NotesContract.NotesEntry.CONTENT_URI;

public class NotesInteractorImpl implements NotesInteractor {

    private Context mContext;

    public NotesInteractorImpl(Context context){
        this.mContext = context;
    }

    @Override
    public void saveNote(OnSaveFinishedListener onSaveFinishedListener, String note) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(COLUMN_NOTE, note);
        mContext.getContentResolver().insert(CONTENT_URI, contentValue);
        onSaveFinishedListener.onSuccess();
    }

    @Override
    public Cursor getNotes() {
        Cursor notesCursor = mContext.getContentResolver().query(NotesContract.NotesEntry.CONTENT_URI, new String[]{COLUMN_NOTE}, null, null, null);

        return notesCursor;
    }
}
