package com.gabrielaangebrandt.tasky;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ListActivtiy extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG ="fsfs" ;
    RecyclerView RV;
    RecyclerView.LayoutManager rvLayoutManager;
    RecyclerView.ItemDecoration rvItemDecoration;
    NoteAdapter noteAdapter;
    Button add;
    String noviNaslov, noviOpis, color;
    int color1;

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
        this.add=(Button) this.findViewById(R.id.rvButton);
        this.RV=(RecyclerView) this.findViewById(R.id.rv);
        this.noteAdapter = new NoteAdapter(this.loadNotes());
        this.rvLayoutManager = new LinearLayoutManager(context);

        /*this.rvItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);

        */
       /* this.RV.addItemDecoration(this.rvItemDecoration);*/
        this.RV.setLayoutManager(this.rvLayoutManager);
        this.RV.setAdapter(this.noteAdapter);

        add.setOnClickListener(this);

        Intent startingIntent = this.getIntent();

        if(startingIntent.hasExtra(NewTask.NOVI_NASLOV)){
            noviNaslov = startingIntent.getStringExtra(NewTask.NOVI_NASLOV);
        }
        if(startingIntent.hasExtra(NewTask.NOVI_OPIS)){
            noviOpis = startingIntent.getStringExtra(NewTask.NOVI_OPIS);

        }
        if(startingIntent.hasExtra(NewTask.BOJA)){
            color = startingIntent.getStringExtra(NewTask.BOJA);
            color1 = Color.parseColor(color);
        }


        Task task = new Task (noviNaslov, noviOpis, color1) ;

        NotesDBHelper.getInstance(getApplicationContext()).insertTask(task);
        NoteAdapter adapter = (NoteAdapter) RV.getAdapter();
        adapter.insert(task);
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
