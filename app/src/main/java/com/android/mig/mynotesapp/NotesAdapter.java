package com.android.mig.mynotesapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{

    private OnClickHandler mOnClickHandler;
    private List<String> mNotesList;

    public NotesAdapter(OnClickHandler onClickHandler){
        this.mOnClickHandler = onClickHandler;
        mNotesList = new ArrayList<>();
    }

    public void setNotes(List<String> notes){
        this.mNotesList = notes;
        notifyDataSetChanged();
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.note_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        holder.mNoteText.setText(mNotesList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mNotesList != null){
            return mNotesList.size();
        }
        return 0;
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mNoteText;
        private Button mDeleteButton;

        public NotesViewHolder(View itemView) {
            super(itemView);
            mNoteText = (TextView) itemView.findViewById(R.id.note_text_view);
            mNoteText.setOnClickListener(this);
            mDeleteButton = (Button) itemView.findViewById(R.id.delete_button);
            mDeleteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.note_text_view:
                    mOnClickHandler.onTextClick(mNoteText.getText().toString());
                    break;
                case R.id.delete_button:
                    mOnClickHandler.onDeleteClick();
                    break;
                default:
                    break;
            }
        }
    }

    public interface OnClickHandler{
        void onTextClick(String note);
        void onDeleteClick();
    }
}
