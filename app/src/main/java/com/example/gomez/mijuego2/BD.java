package com.example.gomez.mijuego2;

/**
 * Created by gomez on 17/11/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BD extends SQLiteOpenHelper {

    //BD PREGUNTAS
    public static final String ID_PREGUNTA ="id_pregunta";
    public static final String PREGUNTA ="pregunta";

    private  static final String DATABASE ="Quiz";
    private  static final String TABLA_PREGUNTAS ="Preguntas";

    //BD RESPUESTAS

    public static final String ID_RESPUESTA ="id_respuesta";
    public static final String RESPUESTA ="respuesta";
    public static final String CORRECTA ="correcta";
    public static final String IDPREGUNTA ="idpregunta";
    private  static final String TABLA_RESPUESTAS ="Respuestas";

    private SQLiteDatabase baseDatos;
    private Context context;


    public BD(Context context) {
        super(context, DATABASE, null, 1);
        Log.i("XXX", "Constructor");
        this.context = context;
        baseDatos = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("XXX", "onCreate");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;

        try{
            inputStream = assetManager.open("crearBD.sql");
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();

            String[] script = outputStream.toString().split("\\n");
            for (String line : script) {
                String sqlStatement = line.trim();
                Log.i("XXX", "Ejecutando sentencia: " + sqlStatement);
                if (sqlStatement.length() > 0) {
                    db.execSQL(sqlStatement + ";");
                }
            }
        } catch (IOException e){
            Log.e("XXX", "Error: " + e.getLocalizedMessage());
        } catch (SQLException e) {
            Log.e("XXX", "Error: " + e.getLocalizedMessage());
        }

        Log.i("XXX", "Base de datos creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        Log.i("XXX", "onUpdate");
        db.execSQL("DROP TABLE IF EXISTS "+ TABLA_PREGUNTAS);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS "+ TABLA_RESPUESTAS);
        onCreate(db);
    }

    public String getPregunta(int id){
        Log.i("XXX", "GET PREGUNTA: " +id);

        Cursor c = baseDatos.rawQuery("SELECT * FROM " + TABLA_PREGUNTAS + " WHERE " + ID_PREGUNTA + " = " + id, null);
        if (c.moveToFirst()){
            do {
                String column1 = c.getString(0);
                String column2 = c.getString(1);
                Log.i("XXX", "Consulta: " + column1 + " - " + column2);

                return column2;
            } while(c.moveToNext());
        }
        return null;
    }

    public ArrayList<String> getRespuestas(int id){
        ArrayList<String> respuestas = new ArrayList<>();

        Cursor c = baseDatos.rawQuery("SELECT * FROM " + TABLA_RESPUESTAS + " WHERE " + IDPREGUNTA + " = " + id, null);
        if (c.moveToFirst()){
            do {
                String column1 = c.getString(0);
                String column2 = c.getString(1);
                Log.i("XXX", "Consulta: " + column1 + " - " + column2);

                respuestas.add(column2);
            } while(c.moveToNext());
        }
        return respuestas;
    }

    public String getRespuestaCorrecta(int id){
        Cursor c = baseDatos.rawQuery("SELECT * FROM " + TABLA_RESPUESTAS + " WHERE " + IDPREGUNTA + " = " + id + " AND " + CORRECTA + " = 1", null);
        if (c.moveToFirst()){
            do {
                String column1 = c.getString(0);
                String column2 = c.getString(1);
                Log.i("XXX", "Consulta: " + column1 + " - " + column2);

                return column2;
            } while(c.moveToNext());
        }
        return null;
    }

    public void introducirPregunta(String pregunta, ArrayList<String> respuestas, int correcta) {
        ContentValues nuevaPregunta = new ContentValues();
        nuevaPregunta.put(PREGUNTA, pregunta);
        long idPregunta = baseDatos.insert(TABLA_PREGUNTAS, null, nuevaPregunta);

        for(int i=0; i<respuestas.size(); i++){
            int buena = 0;
            if(correcta == i) buena = 1;
            baseDatos.execSQL("INSERT INTO " + TABLA_RESPUESTAS + " (respuesta, correcta, idpregunta) VALUES (\"" +
                    respuestas.get(i) + "\", " + buena + ", "+ idPregunta +")");
        }
    }

    public int numeroPreguntas(){
        String countQuery = "SELECT  * FROM " + TABLA_PREGUNTAS;
        Cursor cursor = baseDatos.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public ArrayList<ModeloPregunta> getPreguntas() {
        ArrayList preguntas = new ArrayList();

        String countQuery = "SELECT  * FROM " + TABLA_PREGUNTAS;
        Cursor cursor = baseDatos.rawQuery(countQuery, null);

        while(cursor.moveToNext()){
            preguntas.add(new ModeloPregunta(cursor.getInt(0), cursor.getString(1)));
        }

        return preguntas;
    }

    public void borrarPregunta(ModeloPregunta modeloPregunta) {
        baseDatos.delete(TABLA_PREGUNTAS, ID_PREGUNTA + "=" + modeloPregunta.getId(), null);
        baseDatos.delete(TABLA_RESPUESTAS, IDPREGUNTA + "=" + modeloPregunta.getId(), null);
    }
}
