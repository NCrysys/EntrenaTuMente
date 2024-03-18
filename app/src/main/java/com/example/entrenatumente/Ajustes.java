package com.example.entrenatumente;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Locale;

public class Ajustes extends MainActivity{
    private MainActivity mainActivity;
    private SharedPreferences preferencia;
    private SharedPreferences.Editor editorPreferencia;
    private int tema;
    private int idioma;
    private static String theme = "theme";
    private static String language = "language";
    private static String en = "en";
    private static String es = "es";

    public Ajustes(MainActivity mainActivity) {
        super();
        this.mainActivity = mainActivity;
        preferencia = mainActivity.getSharedPreferences(mainActivity.getPackageName(), MODE_PRIVATE);
    }

    public void obtenerPreferencias(){
        //theme
        AppCompatDelegate.setDefaultNightMode(preferencia.getInt(theme, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM));
        //idioma
        actualizarIdioma(preferencia.getString(language, en));
    }


    public void abrirAjustes() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(mainActivity);
        ventana.setTitle(mainActivity.getResources().getString(R.string.ajustes))
                .setIcon(R.drawable.brain)
                .setItems(R.array.ajustes, seleccionarAjustes)
                .setPositiveButton(mainActivity.getResources().getString(R.string.aceptar), seleccionarAjustes)
                .setCancelable(false)
                .show();
    }
    private DialogInterface.OnClickListener seleccionarAjustes = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which==0){
                themeAjustes();
            } else if (which==1){
                idiomaAjustes();
            }
            else {
                dialog.cancel();
            }
        }
    };

    private void themeAjustes(){
        AlertDialog.Builder ventana = new AlertDialog.Builder(mainActivity);
        ventana.setTitle(mainActivity.getResources().getString(R.string.cambiar_tema))
                .setIcon(R.drawable.brain)
                .setSingleChoiceItems(R.array.themes, 0, seleccionarTema)
                .setPositiveButton(mainActivity.getResources().getString(R.string.aceptar), aceptarTema)
                .setCancelable(false)
                .show();
    }
    private DialogInterface.OnClickListener seleccionarTema = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            tema=which;
        }
    };
    private DialogInterface.OnClickListener aceptarTema = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            editorPreferencia = preferencia.edit();
            if (tema==0){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                editorPreferencia.putInt(theme, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            } else if (tema==1) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editorPreferencia.putInt(theme, AppCompatDelegate.MODE_NIGHT_NO);
            } else if (tema==2) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editorPreferencia.putInt(theme, AppCompatDelegate.MODE_NIGHT_YES);
            }
            editorPreferencia.apply();
        }
    };

    private void idiomaAjustes(){
        AlertDialog.Builder ventana = new AlertDialog.Builder(mainActivity);
        ventana.setTitle(mainActivity.getResources().getString(R.string.cambiar_idioma))
                .setIcon(R.drawable.brain)
                .setSingleChoiceItems(R.array.idiomas, 0, seleccionarIdioma)
                .setPositiveButton(mainActivity.getResources().getString(R.string.aceptar), aceptarIdioma)
                .setCancelable(false)
                .show();
    }
    private DialogInterface.OnClickListener seleccionarIdioma = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            idioma=which;
        }
    };
    private DialogInterface.OnClickListener aceptarIdioma = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            editorPreferencia = preferencia.edit();
            if (idioma==0){
                editorPreferencia.putString(language, en);
                actualizarIdioma(en);
            } else if (idioma==1) {
                editorPreferencia.putString(language, es);
                actualizarIdioma(es);
            }
            editorPreferencia.apply();
            //reiniciarIdioma();
            mainActivity.recreate();
        }
    };

    private void actualizarIdioma(String idiom) {
        Resources recursos = mainActivity.getResources();
        DisplayMetrics displayMetrics = recursos.getDisplayMetrics();
        Configuration configuracion = recursos.getConfiguration();
        configuracion.setLocale(new Locale(idiom));
        recursos.updateConfiguration(configuracion, displayMetrics);
    }

    /*
    private void reiniciarIdioma() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(mainActivity);
        ventana.setTitle(mainActivity.getResources().getString(R.string.atencion))
                .setIcon(R.drawable.brain)
                .setMessage(mainActivity.getResources().getString(R.string.reinicio_idioma))
                .setPositiveButton(mainActivity.getResources().getString(R.string.reiniciar), reiniciarListener)
                .setNegativeButton(mainActivity.getResources().getString(R.string.cancelar), cancelListener)
                .setCancelable(false)
                .show();
    }
    private DialogInterface.OnClickListener reiniciarListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mainActivity.recreate();
        }
    };
    private DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    };
    */
}
