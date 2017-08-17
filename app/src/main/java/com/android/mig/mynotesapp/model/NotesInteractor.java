package com.android.mig.mynotesapp.model;

import java.util.List;

public interface NotesInteractor {

    /**
     * Callback to handle responses from data source transactions
     */
    interface OnSaveFinishedListener{
        void onSuccess();
    }

    /**
     * Return data from data source
     *
     * @return list array of strings
     */
    List<String> getNotes();

    /**
     * Saved a Note into the data source
     *
     * @param onSaveFinishedListener    callback object
     * @param note                      data to be inserted into data source
     */
    void saveNote(OnSaveFinishedListener onSaveFinishedListener, String note);
}
