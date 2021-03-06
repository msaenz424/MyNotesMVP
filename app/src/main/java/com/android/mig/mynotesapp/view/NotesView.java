package com.android.mig.mynotesapp.view;

import android.database.Cursor;

public interface NotesView {

    /**
     * Shows the data into List of Notes
     *
     * @param notes array of notes
     */
    void showNotes(Cursor notes);

}
