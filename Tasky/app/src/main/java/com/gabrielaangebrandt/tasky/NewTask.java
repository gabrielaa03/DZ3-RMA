package com.gabrielaangebrandt.tasky;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

/**
 * Created by Gabriela on 11.4.2017..
 */
public class NewTask extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button button;
    EditText etNaslov;
    Spinner spinner, spinnerKategorija;
    String color, kategorija;
    int color1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtask_layout);
        this.setUpUI();
    }

    private void setUpUI() {
        this.button = (Button) findViewById(R.id.button);
        this.etNaslov = (EditText) findViewById(R.id.etNaslov);
        this.spinner = (Spinner) findViewById(R.id.spinner);
        this.spinnerKategorija = (Spinner) findViewById(R.id.spinnerKategorija);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.prioritet, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.kategorija, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKategorija.setAdapter(adapter1);
        spinnerKategorija.setOnItemSelectedListener(this);

        button.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String odabir = spinner.getSelectedItem().toString();
        kategorija = spinnerKategorija.getSelectedItem().toString();
        switch(odabir){
            case "Visoki":
                color = "#C10808";
                break;
            case "Srednji":
                color = "#EBDB00";
                break;
            case "Niski":
                color = "#048B08";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(),"Niste odabrali prioritet.", Toast.LENGTH_SHORT).show();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        if(Objects.equals(etNaslov.getText().toString(), "") || etNaslov.getText().toString() == null){
            etNaslov.setError("Unesite naziv zadatka.");
        }
        else {
            color1 = Color.parseColor(color);
            Task task = new Task(getTaskId(), etNaslov.getText().toString(), kategorija, color1);
            NotesDBHelper.getInstance(this).insertTask(task);
            Intent intent = new Intent(this, ListActivity.class);
            this.startActivity(intent);

        }
    }




}
