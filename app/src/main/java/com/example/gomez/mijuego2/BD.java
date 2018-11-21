package com.example.gomez.mijuego2;

/**
 * Created by gomez on 17/11/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class BD extends SQLiteOpenHelper {

    //BD PREGUNTAS
    public static final String ID_PREGUNTA ="id_pregunta";
    public static final String PREGUNTA ="pregunta";

    private  static final String DATABASE ="Quiz";
    private  static final String TABLE1 ="Preguntas";

    //BD RESPUESTAS

    public static final String ID_RESPUESTA ="id_respuesta";
    public static final String RESPUESTA ="respuesta";
    public static final String CORRECTA ="correcta";
    public static final String IDPREGUNTA ="idpregunta";
    private  static final String TABLE2 ="Respuestas";

    private SQLiteDatabase baseDatos;


    public BD(Context context) {
        super(context, DATABASE, null, 1);
        Log.i("XXX", "Constructor");
        baseDatos = getWritableDatabase();
    }

    public String getPregunta(int id){
        Log.i("XXX", "GET PREGUNTA: " +id);

        Cursor c = baseDatos.rawQuery("SELECT * FROM " + TABLE1 + " WHERE " + ID_PREGUNTA + " = " + id, null);
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

        Cursor c = baseDatos.rawQuery("SELECT * FROM " + TABLE2 + " WHERE " + IDPREGUNTA + " = " + id, null);
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
        Cursor c = baseDatos.rawQuery("SELECT * FROM " + TABLE2 + " WHERE " + IDPREGUNTA + " = " + id + " AND " + CORRECTA + " = 1", null);
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



    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("XXX", "onCreate");

        db.execSQL("CREATE TABLE "+TABLE1+" ("+
                ID_PREGUNTA+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                PREGUNTA +" TEXT)");


        db.execSQL("CREATE TABLE "+TABLE2+" ("+
                ID_RESPUESTA+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RESPUESTA + " TEXT, "+CORRECTA+ " INTEGER, "+IDPREGUNTA+" INTEGER)");

        db.execSQL("INSERT INTO " + TABLE1 + "(" + PREGUNTA + ") VALUES (\"¿En qué ciudad inglesa nació Noel Gallagher?\")");
        db.execSQL("INSERT INTO " + TABLE2 + "(respuesta, correcta, idpregunta) VALUES (\"Manchester\", 1, 1)");
        db.execSQL("INSERT INTO " + TABLE2 + "(respuesta, correcta, idpregunta) VALUES (\"Liverpool\", 0, 1)");
        db.execSQL("INSERT INTO " + TABLE2 + "(respuesta, correcta, idpregunta) VALUES (\"Londres\", 0, 1)");
        db.execSQL("INSERT INTO " + TABLE2 + "(respuesta, correcta, idpregunta) VALUES (\"Birmingham\", 0, 1)");

        db.execSQL("INSERT INTO " + TABLE1 + "(" + PREGUNTA + ") VALUES (\"¿Que banda publicó el single November Rain en el año 1991?\")");
        db.execSQL("INSERT INTO " + TABLE2 + "(respuesta, correcta, idpregunta) VALUES (\"Led Zepelin\", 0, 2)");
        db.execSQL("INSERT INTO " + TABLE2 + "(respuesta, correcta, idpregunta) VALUES (\"Aerosmith\", 0, 2)");
        db.execSQL("INSERT INTO " + TABLE2 + "(respuesta, correcta, idpregunta) VALUES (\"Guns N' Roses\", 1, 2)");
        db.execSQL("INSERT INTO " + TABLE2 + "(respuesta, correcta, idpregunta) VALUES (\"Metallica\", 0, 2)");

        db.execSQL("INSERT INTO " + TABLE1 + "(" + PREGUNTA + ") VALUES (\"¿Cual de estas bandas no está formada en Reino Unido?\")");
        db.execSQL("INSERT INTO " + TABLE2 + "(respuesta, correcta, idpregunta) VALUES (\"Judas Priest\", 0, 3)");
        db.execSQL("INSERT INTO " + TABLE2 + "(respuesta, correcta, idpregunta) VALUES (\"Motorhead\", 0, 3)");
        db.execSQL("INSERT INTO " + TABLE2 + "(respuesta, correcta, idpregunta) VALUES (\"Megadeth\", 1, 3)");
        db.execSQL("INSERT INTO " + TABLE2 + "(respuesta, correcta, idpregunta) VALUES (\"Black Sabbath\", 0, 3)");


        Log.i("XXX", "Base de datos creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        Log.i("XXX", "onUpdate");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS "+TABLE2);
        onCreate(db);
    }

}
