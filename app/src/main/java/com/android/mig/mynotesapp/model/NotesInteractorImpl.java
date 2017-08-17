package com.android.mig.mynotesapp.model;

import java.util.ArrayList;
import java.util.List;

public class NotesInteractorImpl implements NotesInteractor {

    private List<String> mNotesList;

    public NotesInteractorImpl(){
        mNotesList = new ArrayList<>();
    }

    @Override
    public void saveNote(OnSaveFinishedListener onSaveFinishedListener, String note) {
        if (mNotesList != null){
            mNotesList.add(note);
            onSaveFinishedListener.onSuccess();
        }
    }

    @Override
    public List<String> getNotes() {
        return mNotesList;
    }
}
