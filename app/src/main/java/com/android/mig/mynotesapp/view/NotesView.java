package com.android.mig.mynotesapp.view;

import java.util.List;

public interface NotesView {

    /**
     * Shows the data into List of Notes
     *
     * @param notes array of notes
     */
    void showNotes(List<String> notes);

    void showItem(String note);

    /**
     * Called after a Note was added to the List
     */
    void onAddedNoted();
}
