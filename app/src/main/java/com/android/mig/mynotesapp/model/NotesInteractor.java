package com.android.mig.mynotesapp.model;

import android.database.Cursor;

public interface NotesInteractor {

    /**
     * Callback to handle responses from data source transactions
     */
    interface OnTransactionFinishedListener{
        void onSuccess();
    }

    /**
     * Returns data from data source
     *
     * @return a cursor
     */
    Cursor getNotes();

    /**
     * Save a Note into the data source
     *
     * @param onTransactionFinishedListener    callback object
     * @param note                             data to be inserted into data source
     */
    void saveNote(OnTransactionFinishedListener onTransactionFinishedListener, String note);

    /**
     * Delete a single row from data source
     *
     * @param onTransactionFinishedListener     callback object
     * @param id                                note id
     */
    void deleteNote(OnTransactionFinishedListener onTransactionFinishedListener, String id);
}
