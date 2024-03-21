package com.example.entrenatumente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class ActivityJuegoParejas extends AppCompatActivity {
    private ImageView imgBrainJuegoParejas;
    private Button btnAtras, btnOtro;
    private ToggleButton casilla1, casilla2, casilla3, casilla4,
                        casilla5, casilla6, casilla7, casilla8,
                        casilla9, casilla10, casilla11, casilla12,
                        casilla13, casilla14, casilla15, casilla16,
                        casilla17, casilla18, casilla19, casilla20;
    private ToggleButton[] arrayCasillas;
    private String table;
    private Parejas parejas;
    private int numParejas=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_parejas);
        findsYLinsteners();
        parejas = new Parejas(arrayCasillas);
        Intent intent=getIntent();
        table = intent.getStringExtra("table");
        if (table==null){
            parejas.crearParejasDibujos();
        } else if (table.equals("R")) {
            parejas.crearParejasTablaRandom();
        } else {
            parejas.crearParejasTabla(table);
        }
    }

    private void findsYLinsteners() {
        //finds
        imgBrainJuegoParejas = findViewById(R.id.imgBrainJuegoParejas);
        btnAtras=findViewById(R.id.btnAtras);
        btnOtro=findViewById(R.id.btnOtro);
        //listeners
        imgBrainJuegoParejas.setOnClickListener(inicio);
        btnAtras.setOnClickListener(atrasOtro);
        btnOtro.setOnClickListener(atrasOtro);
        //casillas
        //finds
        casilla1=findViewById(R.id.casilla1);
        casilla2=findViewById(R.id.casilla2);
        casilla3=findViewById(R.id.casilla3);
        casilla4=findViewById(R.id.casilla4);
        casilla5=findViewById(R.id.casilla5);
        casilla6=findViewById(R.id.casilla6);
        casilla7=findViewById(R.id.casilla7);
        casilla8=findViewById(R.id.casilla8);
        casilla9=findViewById(R.id.casilla9);
        casilla10=findViewById(R.id.casilla10);
        casilla11=findViewById(R.id.casilla11);
        casilla12=findViewById(R.id.casilla12);
        casilla13=findViewById(R.id.casilla13);
        casilla14=findViewById(R.id.casilla14);
        casilla15=findViewById(R.id.casilla15);
        casilla16=findViewById(R.id.casilla16);
        casilla17=findViewById(R.id.casilla17);
        casilla18=findViewById(R.id.casilla18);
        casilla19=findViewById(R.id.casilla19);
        casilla20=findViewById(R.id.casilla20);
        //Array
        arrayCasillas = new ToggleButton[] {casilla1, casilla2, casilla3, casilla4,
                casilla5, casilla6, casilla7, casilla8,
                casilla9, casilla10, casilla11, casilla12,
                casilla13, casilla14, casilla15, casilla16,
                casilla17, casilla18, casilla19, casilla20};
        //listeners
        for (int i = 0; i < arrayCasillas.length; i++) {
            arrayCasillas[i].setOnClickListener(parejaSeleccionada);
        }
    }

    private View.OnClickListener parejaSeleccionada = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (((ToggleButton) v).isChecked()){
                if (parejas.getParejaSeleccionada1() == null) {
                    parejas.setParejaSeleccionada1((ToggleButton) v);
                } else {
                    if (parejas.comprobarPareja((ToggleButton) v)) {
                        numParejas++;
                        if (numParejas==10){
                            btnOtro.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }
    };

    private View.OnClickListener atrasOtro = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==btnAtras.getId()){
                finish();
            }
            else if (v.getId()==btnOtro.getId()){
                btnOtro.setVisibility(View.GONE);
                numParejas=0;
                if (table==null){
                    parejas.crearParejasDibujos();
                } else if (table.equals("R")) {
                    parejas.crearParejasTablaRandom();
                } else {
                    parejas.crearParejasTabla(table);
                }
            }
        }
    };

    private View.OnClickListener inicio = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setResult(RESULT_OK);
            finish();
        }
    };
}