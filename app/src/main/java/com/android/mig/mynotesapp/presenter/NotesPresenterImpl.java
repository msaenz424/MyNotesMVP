package com.android.mig.mynotesapp.presenter;

import android.content.Context;

import com.android.mig.mynotesapp.model.NotesInteractor;
import com.android.mig.mynotesapp.model.NotesInteractorImpl;
import com.android.mig.mynotesapp.view.NotesView;

public class NotesPresenterImpl implements NotesPresenter, NotesInteractor.OnSaveFinishedListener{

    private NotesView mNotesView;
    private NotesInteractor mNotesInteractor;

    public NotesPresenterImpl(NotesView notesView){
        this.mNotesView = notesView;
        this.mNotesInteractor = new NotesInteractorImpl((Context) notesView);
    }

    @Override
    public void onItemClicked(String note) {

    }

    @Override
    public void addNote(String note) {
        mNotesInteractor.saveNote(this, note);
    }

    @Override
    public void onSuccess() {
        mNotesView.showNotes(mNotesInteractor.getNotes());
        mNotesView.onAddedNoted();
    }
}
