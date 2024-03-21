package com.example.entrenatumente;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class ActivityParejas extends AppCompatActivity {
    private ImageView imgBrainParejas;
    private Button btnParejasNormal, btnParejasMultiply;
    private Spinner spnTabla;
    private String table = "R";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parejas);
        findsYLinsteners();
    }

    private void findsYLinsteners() {
        //finds
        imgBrainParejas = findViewById(R.id.imgBrainParejas);
        btnParejasNormal = findViewById(R.id.btnParejasNormal);
        btnParejasMultiply = findViewById(R.id.btnParejasMultiply);
        spnTabla = findViewById(R.id.spnTabla);
        //listeners
        imgBrainParejas.setOnClickListener(inicio);
        btnParejasNormal.setOnClickListener(eligeParejas);
        btnParejasMultiply.setOnClickListener(eligeParejas);
        spnTabla.setOnItemSelectedListener(seleccionarTabla);
    }

    private AdapterView.OnItemSelectedListener seleccionarTabla = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            table = String.valueOf(parent.getItemAtPosition(position).toString().charAt(0));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private View.OnClickListener eligeParejas = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ActivityParejas.this, ActivityJuegoParejas.class);
            if (v.getId()==btnParejasNormal.getId()){
                activityResultLauncher.launch(intent);
            }
            else if (v.getId()==btnParejasMultiply.getId()){
                intent.putExtra("table", table);
                activityResultLauncher.launch(intent);
            }
        }
    };

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        //Intent intent = result.getData();
                        finish();
                    }
                }
            });

    private View.OnClickListener inicio = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}