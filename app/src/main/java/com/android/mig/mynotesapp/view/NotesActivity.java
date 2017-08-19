package com.android.mig.mynotesapp.view;

import android.database.Cursor;
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

public class NotesActivity extends AppCompatActivity implements NotesView, NotesAdapter.OnClickHandler{

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
        mNotesAdapter = new NotesAdapter(this);
        mNotesRecyclerView.setAdapter(mNotesAdapter);

        mNotesPresenter = new NotesPresenterImpl(this);
        mNotesPresenter.loadNotes();
    }

    @Override
    public void showNotes(Cursor notes) {
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

    @Override
    public void onTextClick(String note) {
        Toast.makeText(this, note, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick() {

    }
}
