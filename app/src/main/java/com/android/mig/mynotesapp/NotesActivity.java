package com.android.mig.mynotesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class NotesActivity extends AppCompatActivity implements NotesView{

    RecyclerView mNotesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        mNotesRecyclerView = (RecyclerView) findViewById(R.id.notes_recycler_view);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mNotesRecyclerView.setLayoutManager(linearLayout);
        NotesAdapter mNotesAdapter = new NotesAdapter();
        mNotesRecyclerView.setAdapter(mNotesAdapter);
    }

    @Override
    public void showNotes(List<String> notes) {
        NotesAdapter notesAdapter = (NotesAdapter) mNotesRecyclerView.getAdapter();
        notesAdapter.setNotes(notes);
    }
}
