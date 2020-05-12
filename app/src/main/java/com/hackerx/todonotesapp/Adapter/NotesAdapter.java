package com.hackerx.todonotesapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.hackerx.todonotesapp.Notes;
import com.hackerx.todonotesapp.R;

import java.util.List;

import ClickListener.ItemClickListener;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Notes> listNotes;
    private ItemClickListener itemClickListener;
    public NotesAdapter(List<Notes> list, ItemClickListener itemClickListener){
        this.listNotes = list;
        this.itemClickListener = itemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView title , description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.card_title_text);
            description = itemView.findViewById(R.id.card_desc_text);
        }
    }


    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_adapter_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        String title,description;
       final  Notes notes = listNotes.get(position);
        title = notes.getTitle();
        description = notes.getDescription();

        holder.title.setText(title);
        holder.description.setText(description);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onClick(notes);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listNotes.size();
    }


}
