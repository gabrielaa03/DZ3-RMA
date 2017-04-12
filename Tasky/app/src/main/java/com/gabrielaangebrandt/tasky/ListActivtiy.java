package com.gabrielaangebrandt.tasky;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ListActivtiy extends AppCompatActivity implements View.OnClickListener {

     public RecyclerView RV;
    RecyclerView.LayoutManager rvLayoutManager;
    NoteAdapter noteAdapter;
    Button add;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_list_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setUpUI();

    }

    private void setUpUI() {

        Context context = getApplicationContext();
        this.add = (Button) this.findViewById(R.id.rvButton);
        this.RV = (RecyclerView) this.findViewById(R.id.rv);
        this.rvLayoutManager = new LinearLayoutManager(context);
        this.noteAdapter = new NoteAdapter(this.loadNotes());
        RV.addItemDecoration(new SimpleDividerItemDecoration(this));

        this.RV.setLayoutManager(this.rvLayoutManager);
        this.RV.setAdapter(this.noteAdapter);

        add.setOnClickListener(this);

    }
    private ArrayList<Task> loadNotes() {
        return NotesDBHelper.getInstance(this).getAllNotes();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, NewTask.class);
        startActivity(intent);
    }
}
