package com.gabrielaangebrandt.tasky;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gabriela on 8.4.2017..
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    ArrayList<Task> alTasks;
    NotesDBHelper notesDBHelper;
    Context context;


    public NoteAdapter(ArrayList<Task> biljeske) {
        alTasks = biljeske;

    }

    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.task_layout, parent, false);
        ViewHolder noteViewHolder = new ViewHolder(view);
        return noteViewHolder;

    }

    @Override
    public void onBindViewHolder(NoteAdapter.ViewHolder holder, int position) {
        Task zadatak = this.alTasks.get(position);
        holder.tvNaslov.setText(zadatak.getNaslov());
        holder.tvBiljeska.setText(zadatak.getOpis());
        holder.imPrioritet.setBackgroundColor(zadatak.getPrioritet());
    }

    @Override
    public int getItemCount() {
        return this.alTasks.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        public TextView tvNaslov, tvBiljeska;
        public ImageView imPrioritet;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvNaslov = (TextView) itemView.findViewById(R.id.tvNaslov);
            this.tvBiljeska = (TextView) itemView.findViewById(R.id.tvBiljeska);
            this.imPrioritet = (ImageView) itemView.findViewById(R.id.tvPrioritet);

            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            NotesDBHelper notesDBHelper = new NotesDBHelper(context);
        //    int position = alTasks.get(getAdapterPosition()).getID();
            notesDBHelper.deleteEntry(alTasks.get(getAdapterPosition()).getID());
           // boolean isDeleted = notesDBHelper.deleteEntry(Long.parseLong(position));

                alTasks.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());

                notifyDataSetChanged();

            return false ;
        }
        }
    }

