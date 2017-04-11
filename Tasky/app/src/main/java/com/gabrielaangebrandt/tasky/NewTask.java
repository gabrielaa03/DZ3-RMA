package com.gabrielaangebrandt.tasky;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Gabriela on 11.4.2017..
 */
public class NewTask extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static final String NOVI_NASLOV =" naslov" ;
    public static final String NOVI_OPIS = "opis";
    public static final String BOJA = "boja";
    Button button;
    EditText etNaslov, etOpis;
    Spinner spinner;
    String noviNaslov, noviOpis, color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtask_layout);
        this.setUpUI();
    }

    private void setUpUI() {
        this.button = (Button) findViewById(R.id.button);
        this.etNaslov = (EditText) findViewById(R.id.etNaslov);
        this.etOpis = (EditText) findViewById(R.id.etOpis);
        this.spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.prioritet, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        button.setOnClickListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String odabir = spinner.getSelectedItem().toString();
        switch(odabir){
            case "Visoki":
                color = "#FC0606";
                break;
            case "Srednji":
                color = "#FCFC06";
                break;
            case "Niski":
                color = "#2FD805";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(),"Niste odabrali prioritet.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ListActivtiy.class);
        intent.putExtra(NOVI_NASLOV, etNaslov.getText().toString());
        intent.putExtra(NOVI_OPIS,  etOpis.getText().toString());
        intent.putExtra(BOJA, color);

        this.startActivity(intent);
    }




}
