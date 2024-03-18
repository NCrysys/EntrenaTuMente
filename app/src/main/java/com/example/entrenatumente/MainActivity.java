package com.example.entrenatumente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout llPantallaInicial, llInicio;
    private ImageView imgBrain, imgAjustes;
    private Button btnComenzar;
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
        imgBrain=findViewById(R.id.imgBrain);
        imgAjustes=findViewById(R.id.imgAjustes);
        btnComenzar=findViewById(R.id.btnComenzar);
        //listeners
        btnComenzar.setOnClickListener(comenzar);
        imgAjustes.setOnClickListener(configuracion);
    }

    private View.OnClickListener comenzar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            llPantallaInicial.setVisibility(View.GONE);
            llInicio.setVisibility(View.VISIBLE);
        }
    };

    private View.OnClickListener configuracion = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ajustes.abrirAjustes();
        }
    };
}