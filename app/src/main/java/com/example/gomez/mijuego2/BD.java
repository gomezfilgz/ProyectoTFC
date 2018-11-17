package com.example.gomez.mijuego2;

/**
 * Created by gomez on 17/11/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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


    public BD(Context context, String database) {

        super(context, database, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE1+" ("+
                ID_PREGUNTA+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                PREGUNTA +" TEXT)");


        db.execSQL("CREATE TABLE "+TABLE2+" ("+
                ID_RESPUESTA+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RESPUESTA + " TEXT, "+CORRECTA+ " INTEGER, "+IDPREGUNTA+" INTEGER)");
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS "+TABLE2);
        onCreate(db);
    }

    //BD PREGUNTAS
    public void addPregunta(String pregunta){
        ContentValues values = new ContentValues();
        values.put(PREGUNTA,pregunta);
        this.getWritableDatabase().insert(TABLE1,null,values);
    }

    public Cursor getPregunta(String condition){
        String col[] ={ID_PREGUNTA,PREGUNTA};
        String[] args = new String[] {condition};
        Cursor c = this.getReadableDatabase().query(TABLE1,col, PREGUNTA+"=?",args,null,null, ID_PREGUNTA+" DESC");
        return c;
    }

    public Cursor getPreguntas(){
        String col[] ={ID_PREGUNTA,PREGUNTA};
        Cursor c = this.getReadableDatabase().query(TABLE1,col,null,null,null,null,ID_PREGUNTA+" DESC");
        return c;
    }

    public void deletePreguntas(String condition){
        String[]args = {condition};
        this.getWritableDatabase().delete(TABLE1,ID_PREGUNTA+"=?",args);
    }

    //BD RESPUESTAS
    public void addRespuestas(String respuesta,int correcta,int idpregunta){
        ContentValues values = new ContentValues();
        values.put(RESPUESTA,respuesta);
        values.put(CORRECTA,correcta);
        values.put(IDPREGUNTA,idpregunta);
        this.getWritableDatabase().insert(TABLE2,null,values);
    }

    public Cursor getRespuesta(String condition){
        String col[] ={ID_RESPUESTA,RESPUESTA,CORRECTA,IDPREGUNTA};
        String[] args = new String[] {condition};
        Cursor c = this.getReadableDatabase().query(TABLE2,col, RESPUESTA+"=?",args,null,null, ID_RESPUESTA+" DESC");
        return c;
    }

    public Cursor getRespuestas(){
        String col[] ={ID_RESPUESTA,RESPUESTA,CORRECTA,IDPREGUNTA};
        Cursor c = this.getReadableDatabase().query(TABLE2,col,null,null,null,null,ID_RESPUESTA+" DESC");
        return c;
    }

    public void deleteRespuesta(String condition){
        String[]args = {condition};
        this.getWritableDatabase().delete(TABLE2,ID_RESPUESTA+"=?",args);
    }

    /*public Cursor getPreguntayrespuestas(String condition){
        String col[] ={ID_RESPUESTA,RESPUESTA,CORRECTA,IDPREGUNTA};
        String[] args = new String[] {condition};
        Cursor c = this.getReadableDatabase().query(TABLE2,col, RESPUESTA+"=?",args,null,null, ID_RESPUESTA+" DESC");
        return c;
    }
    */
}
