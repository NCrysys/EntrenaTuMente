package com.example.entrenatumente;

import android.os.Handler;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class Parejas {
    private ToggleButton[] arrayCasillas;
    private String[] arrayParejasTabla;
    private ArrayList<Integer> arrayPosicionParejasRepartidas;
    private ToggleButton parejaSeleccionada1 = null;

    public Parejas(ToggleButton[] arrayCasillas) {
        this.arrayCasillas = arrayCasillas;
    }

    public void crearParejasTablaRandom() {
        arrayParejasTabla = new String[20];
        for (int i = 0; i < arrayParejasTabla.length; i++) {
            if (i<(arrayParejasTabla.length/2)) {
                arrayParejasTabla[i] = randomNumber() + "×" + randomNumber();
            }
            else {
                arrayParejasTabla[i] = muliplicar(arrayParejasTabla[i-10]);
            }
        }
        repartirParejasTabla();
    }

    private int randomNumber() {
        return (int) Math.floor(Math.random()*(10-1+1)+1);
    }

    private String muliplicar(String multiplicacion) {
        int n1 = Integer.parseInt(multiplicacion.split("×")[0]);
        int n2 = Integer.parseInt(multiplicacion.split("×")[1]);
        return String.valueOf(n1*n2);
    }

    public void crearParejasTabla(String table) {
        arrayParejasTabla = new String[20];
        for (int i = 0; i < arrayParejasTabla.length; i++) {
            if (i<(arrayParejasTabla.length/2)) {
                arrayParejasTabla[i] = table + "×" + (i+1);
            }
            else {
                arrayParejasTabla[i] = String.valueOf(Integer.parseInt(table) * (i-9));
            }
        }
        repartirParejasTabla();
    }

    private void repartirParejasTabla() {
        arrayPosicionParejasRepartidas = new ArrayList();
        for (int i = 0; i < arrayCasillas.length; i++) {
            arrayCasillas[i].setTextOn(getParejaRandom());
            arrayCasillas[i].setTextOff("");
        }
    }

    private String getParejaRandom() {
        int posicionPareja=-1;
        boolean yaRepartida;
        do {
            yaRepartida=false;
            posicionPareja = (int) Math.floor(Math.random()*(19-0+1)+0);
            for (int i = 0; i < arrayPosicionParejasRepartidas.size(); i++) {
                if (posicionPareja== arrayPosicionParejasRepartidas.get(i)){
                    yaRepartida=true;
                }
            }
        } while (yaRepartida);
        arrayPosicionParejasRepartidas.add(posicionPareja);
        return arrayParejasTabla[posicionPareja];
    }

    public void setParejaSeleccionada1(ToggleButton parejaSeleccionada1) {
        this.parejaSeleccionada1=parejaSeleccionada1;
        parejaSeleccionada1.setClickable(false);
    }

    public ToggleButton getParejaSeleccionada1() {
        return parejaSeleccionada1;
    }

    public boolean comprobarPareja(ToggleButton parejaSeleccionada2) {
        parejaSeleccionada2.setChecked(true);
        String pareja1 = String.valueOf(parejaSeleccionada1.getTextOn());
        String pareja2 = String.valueOf(parejaSeleccionada2.getTextOn());
        boolean sonPareja;
        if (pareja1.contains("×") && pareja2.contains("×") ||
            !pareja1.contains("×") && !pareja2.contains("×")){
            sonPareja = false;
        }
        else {
            if (pareja1.contains("×")) {
                pareja1 = muliplicar(pareja1);
            }
            if (pareja2.contains("×")) {
                pareja2 = muliplicar(pareja2);
            }
            sonPareja = pareja1.equals(pareja2);
        }
        if (sonPareja) {
            parejaSeleccionada1.setClickable(false);
            parejaSeleccionada2.setClickable(false);
            parejaSeleccionada1=null;
        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    // acciones que se ejecutan tras los milisegundos
                    parejaSeleccionada1.setClickable(true);
                    parejaSeleccionada1.setChecked(false);
                    parejaSeleccionada2.setChecked(false);
                    parejaSeleccionada1=null;
                }
            }, 1000);
        }
        return sonPareja;
    }

    private static void esperar (int segundos){
        try {
            Thread.sleep(segundos * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
