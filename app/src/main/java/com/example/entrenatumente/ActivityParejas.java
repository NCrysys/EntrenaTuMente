package com.example.entrenatumente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class ActivityParejas extends AppCompatActivity {
    private Button btnParejasNormal, btnParejasMultiply;
    private Spinner spnTabla;
    private String tabla = "R";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parejas);
        findsYLinsteners();
    }

    private void findsYLinsteners() {
        //finds
        btnParejasNormal = findViewById(R.id.btnParejasNormal);
        btnParejasMultiply = findViewById(R.id.btnParejasMultiply);
        spnTabla = findViewById(R.id.spnTabla);
        //listeners
        btnParejasNormal.setOnClickListener(eligeParejas);
        btnParejasMultiply.setOnClickListener(eligeParejas);
        spnTabla.setOnItemSelectedListener(seleccionarTabla);
    }

    private View.OnClickListener eligeParejas = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ActivityParejas.this, ActivityJuegoParejas.class);
            if (v.getId()==btnParejasNormal.getId()){
                startActivity(intent);
            }
            else if (v.getId()==btnParejasMultiply.getId()){
                if (tabla.equals("R")){

                } else {

                }
                intent.putExtra("table", tabla);
                startActivity(intent);
            }
        }
    };

    private AdapterView.OnItemSelectedListener seleccionarTabla = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            tabla = String.valueOf(parent.getItemAtPosition(position).toString().charAt(0));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}