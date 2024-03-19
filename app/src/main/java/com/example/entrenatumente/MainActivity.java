package com.example.entrenatumente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout llPantallaInicial, llInicio;
    private ImageView imgAjustes;
    private Button btnComenzar, btnParejas, btnCromatico;
    private Ajustes ajustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ajustes = new Ajustes(this);
        ajustes.obtenerPreferencias();
        setContentView(R.layout.activity_main);
        findsYLinsteners();
    }

    private void findsYLinsteners() {
        //finds
        llPantallaInicial=findViewById(R.id.llPantallaInicial);
        llInicio=findViewById(R.id.llInicio);
        imgAjustes=findViewById(R.id.imgAjustes);
        btnComenzar=findViewById(R.id.btnComenzar);
        btnParejas=findViewById(R.id.btnParejas);
        btnCromatico=findViewById(R.id.btnCromatico);
        //listeners
        imgAjustes.setOnClickListener(configuracion);
        btnComenzar.setOnClickListener(comenzar);
        btnParejas.setOnClickListener(eligeJuego);
        btnCromatico.setOnClickListener(eligeJuego);
    }

    public View.OnClickListener configuracion = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ajustes.abrirAjustes();
        }
    };

    private View.OnClickListener comenzar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            llPantallaInicial.setVisibility(View.GONE);
            llInicio.setVisibility(View.VISIBLE);
        }
    };

    private View.OnClickListener eligeJuego = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==btnParejas.getId()){
                Intent intent = new Intent(MainActivity.this, ActivityParejas.class);
                startActivity(intent);
            }
            else if (v.getId()==btnCromatico.getId()){

            }
        }
    };
}