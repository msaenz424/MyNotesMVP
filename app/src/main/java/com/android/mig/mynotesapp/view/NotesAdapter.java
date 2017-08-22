package com.android.mig.mynotesapp.view;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.mig.mynotesapp.R;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{

    private final int ID_COLUMN_POSITION = 0;
    private final int NOTE_COLUMN_POSITION = 1;
    private OnClickHandler mOnClickHandler;
    private Cursor mNotesCursor;

    public NotesAdapter(OnClickHandler onClickHandler){
        this.mOnClickHandler = onClickHandler;
    }

    public void setNotes(Cursor notes){
        this.mNotesCursor = notes;
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
        if (mNotesCursor != null) {
            mNotesCursor.moveToPosition(position);
            holder.mNoteText.setText(mNotesCursor.getString(NOTE_COLUMN_POSITION));
        }
    }

    @Override
    public int getItemCount() {
        if (mNotesCursor != null){
            return mNotesCursor.getCount();
        }
        return 0;
    }

    class NotesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mNoteText;
        private Button mDeleteButton;

        private NotesViewHolder(View itemView) {
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
                    mNotesCursor.moveToPosition(getAdapterPosition());
                    mOnClickHandler.onDeleteClick(mNotesCursor.getString(ID_COLUMN_POSITION));
                    break;
                default:
                    break;
            }
        }
    }

    public interface OnClickHandler{
        void onTextClick(String note);
        void onDeleteClick(String id);
    }
}
