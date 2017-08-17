package com.android.mig.mynotesapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.mig.mynotesapp.NotesAdapter;
import com.android.mig.mynotesapp.R;
import com.android.mig.mynotesapp.presenter.NotesPresenter;
import com.android.mig.mynotesapp.presenter.NotesPresenterImpl;

import java.util.List;

public class NotesActivity extends AppCompatActivity implements NotesView{

    EditText mNoteEditText;

    RecyclerView mNotesRecyclerView;
    NotesAdapter mNotesAdapter;
    NotesPresenter mNotesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        mNoteEditText = (EditText) findViewById(R.id.note_edit_text);

        mNotesRecyclerView = (RecyclerView) findViewById(R.id.notes_recycler_view);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mNotesRecyclerView.setLayoutManager(linearLayout);
        mNotesAdapter = new NotesAdapter();
        mNotesRecyclerView.setAdapter(mNotesAdapter);

        mNotesPresenter = new NotesPresenterImpl(this);
    }

    @Override
    public void showNotes(List<String> notes) {
        NotesAdapter notesAdapter = (NotesAdapter) mNotesRecyclerView.getAdapter();
        notesAdapter.setNotes(notes);
    }

    @Override
    public void showItem(String note) {

    }

    @Override
    public void onAddedNoted() {
        mNoteEditText.setText("");
    }

    /**
     * Called when clicked on Add Button
     */
    public void addNote(View view){
        mNotesPresenter.addNote(mNoteEditText.getText().toString());
    }
}
