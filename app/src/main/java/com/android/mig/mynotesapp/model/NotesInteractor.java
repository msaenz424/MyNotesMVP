package com.android.mig.mynotesapp.model;

import android.database.Cursor;

public interface NotesInteractor {

    /**
     * Callback to handle responses from data source transactions
     */
    interface OnSaveFinishedListener{
        void onSuccess();
    }

    /**
     * Returns data from data source
     *
     * @return a cursor
     */
    Cursor getNotes();

    /**
     * Saved a Note into the data source
     *
     * @param onSaveFinishedListener    callback object
     * @param note                      data to be inserted into data source
     */
    void saveNote(OnSaveFinishedListener onSaveFinishedListener, String note);
}
