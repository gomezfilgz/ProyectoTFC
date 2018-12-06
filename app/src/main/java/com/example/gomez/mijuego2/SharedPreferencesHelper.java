package com.example.gomez.mijuego2;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesHelper {

    static String PREFS_NAME = "OPCIONES";
    static String TIEMPO_KEY = "TIEMPO";
    static String FIN_KEY = "FIN";
    static String RANDOM_KEY = "RANDOM";


    public static void guardarTiempo(Context context, int tiempo){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(TIEMPO_KEY, tiempo);
        editor.apply();
    }

    public static int recuperarTiempo(Context context){
        return context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).getInt(TIEMPO_KEY, 10);
    }

    public static void guardarFinTrasError(Context context, boolean value){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(FIN_KEY, value);
        editor.apply();
    }

    public static boolean recuperarFinTrasError(Context context){
        return context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).getBoolean(FIN_KEY, false);
    }

    public static void guardarRandom(Context context, boolean value){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(RANDOM_KEY, value);
        editor.apply();
    }

    public static boolean recuperarRandom(Context context){
        return context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).getBoolean(RANDOM_KEY, false);
    }

}
