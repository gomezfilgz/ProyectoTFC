package com.example.gomez.mijuego2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by gomez on 15/02/2018.
 */

public class Escena2 extends Escena {

    Bitmap fondo,btnBack,reloj;
    Rect btnVolver,btnRespuesta1,btnRespuesta2,btnRespuesta3,btnRespuesta4;
    Paint rect,letras;
    CountDownTimer contador;
    long cont=0;
    int respuesta_seleccionada,respuesta_correcta;
    boolean tiempo_atras;

    public Escena2(int numEscena, Context context, int colorFondo, int anchoPantalla, int altoPantalla) {
        super(numEscena, context, colorFondo, anchoPantalla, altoPantalla);
        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.guitar);
        btnVolver = new Rect(anchoPantalla * 5 / 100, altoPantalla * 15 / 100, anchoPantalla * 20 / 100, altoPantalla * 30 / 100);

        btnRespuesta1 = new Rect(anchoPantalla * 5 / 100, altoPantalla * 55 / 100, anchoPantalla * 45 / 100, altoPantalla * 70 / 100);
        btnRespuesta2 = new Rect(anchoPantalla * 5 / 100, altoPantalla * 75 / 100, anchoPantalla * 45 / 100, altoPantalla * 90 / 100);
        btnRespuesta3 = new Rect(anchoPantalla * 55 / 100, altoPantalla * 55 / 100, anchoPantalla * 95 / 100, altoPantalla * 70 / 100);
        btnRespuesta4 = new Rect(anchoPantalla * 55 / 100, altoPantalla * 75 / 100, anchoPantalla * 95 / 100, altoPantalla * 90 / 100);
        btnBack = super.escala(R.drawable.back, 50, 50);
        reloj = super.escala(R.drawable.reloj, 70, 70);


        rect = new Paint();
        rect.setColor(Color.BLACK);
        rect.setAlpha(200);

        letras = new Paint();
        letras.setAlpha(255);
        letras.setColor(Color.BLACK);
        letras.setTextSize(getPixels(30));

        btnVolver = new Rect(anchoPantalla * 10 / 100, altoPantalla * 10 / 100, anchoPantalla * 25 / 100, altoPantalla * 20 / 100);
        cont = 20;
        inicializaContador();

    }

    public void dibujar(Canvas c){
        super.dibujar(c);
        inicializaContador();
        c.drawBitmap(super.escala(R.drawable.quiz,anchoPantalla,altoPantalla), 0, 0, null);
        c.drawRect(btnVolver, rect);
        c.drawBitmap(btnBack,anchoPantalla*10/100,altoPantalla*10/100,null);
        c.drawBitmap(reloj,anchoPantalla*75/100,altoPantalla*5/100,null);
        c.drawRect(btnRespuesta1, rect);
        c.drawRect(btnRespuesta2, rect);
        c.drawRect(btnRespuesta3, rect);
        c.drawRect(btnRespuesta4, rect);
        c.drawText(""+cont,anchoPantalla*83/100,altoPantalla*15/100,letras);

    }
    public void actualizarFisica(){
        super.actualizarFisica();
    }

    private void inicializaContador(){
        contador = new CountDownTimer(cont*20000, 1000) {
            @Override
            public void onTick(final long l) {
                tiempo_atras=true;
                cont=(l/1000);
                Log.i("Cont", cont+"");
                //Pendiente de arreglar la cuenta atrás
            }


            public void onFinish() {
                tiempo_atras=false;

            }
        };


    }
    public int onTouchEvent(MotionEvent event) {
        int accion=event.getAction();
        int y = (int) event.getY();
        int x = (int) event.getX();
        switch (accion){
            case MotionEvent.ACTION_DOWN:
                if(btnVolver.contains(x,y))
                    return  1;
                if(btnRespuesta1.contains(x,y)){
                    respuesta_seleccionada=1;
                    Log.i("Respuesta correcta=",respuesta_correcta+"");
                }
                if(btnRespuesta2.contains(x,y)){
                    respuesta_seleccionada=2;
                    Log.i("Respuesta correcta=",respuesta_correcta+"");
                }
                if(btnRespuesta3.contains(x,y)){
                    respuesta_seleccionada=3;
                    Log.i("Respuesta correcta=",respuesta_correcta+"");
                }
                if(btnRespuesta4.contains(x,y)){
                    respuesta_seleccionada=4;
                    Log.i("Respuesta correcta=",respuesta_correcta+"");
                }
                break;
        }
        return numEscena;
    }
}