package com.android.mig.mynotesapp.presenter;

public interface NotesPresenter {

    void onItemClicked(String note);

    void loadNotes();

    void addNote(String note);

    void deleteNote(String id);
}
