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
    Paint rect,rect2,rect3,letras,letras2;
    long cont=0;
    CountDownTimer contador;
    int respuesta_seleccionada,respuesta_correcta;
    boolean tiempo_atras,finaltiempo,inicial=true;

    public Escena2(int numEscena, Context context, int colorFondo, int anchoPantalla, int altoPantalla) {
        super(numEscena, context, colorFondo, anchoPantalla, altoPantalla);
        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.guitarmastil);
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

        rect2= new Paint();
        rect2.setColor(Color.BLACK);
        rect2.setAlpha(0);

        rect3 = new Paint();
        rect3.setAlpha(40);
        rect3.setColor(Color.WHITE);

        letras = new Paint();
        letras.setAlpha(255);
        letras.setColor(Color.BLACK);
        letras.setTextSize(getPixels(30));

        letras2 = new Paint();
        letras2.setAlpha(255);
        letras2.setColor(Color.WHITE);
        letras2.setTextSize(getPixels(20));

        cont = 20;
        inicializaContador();


    }

    public void dibujar(Canvas c){
        try{
        super.dibujar(c);

        c.drawBitmap(super.escala(R.drawable.guitarmastil,anchoPantalla,altoPantalla), 0, 0, null);
        c.drawRect(btnVolver, rect2);
        c.drawBitmap(btnBack,anchoPantalla*10/100,altoPantalla*10/100,null);
        c.drawBitmap(reloj,anchoPantalla*75/100,altoPantalla*5/100,null);
        c.drawText("Tiempo: ",anchoPantalla*45/100,altoPantalla*40/100,letras2);
        c.drawText(""+cont,anchoPantalla*45/100,altoPantalla*50/100,letras2);
        c.drawRect(btnRespuesta1, rect);
        c.drawRect(btnRespuesta2, rect);
        c.drawRect(btnRespuesta3, rect);
        c.drawRect(btnRespuesta4, rect);
        c.drawText(""+cont,anchoPantalla*80/100,altoPantalla*15/100,letras);

            if (inicial){
                c.drawRect(0,0,anchoPantalla,altoPantalla,rect3);
                c.drawText("Comenzar",anchoPantalla*15/100,altoPantalla*40/100,letras);
                //Estado inicial antes de comenzar la partida
            }

    }catch (NullPointerException e){}

    }
    public void actualizarFisica(){
        super.actualizarFisica();
    }

    private void inicializaContador(){
        contador = new CountDownTimer(cont*1000, 1000) {
            @Override
            /**
             * Metodo que se ejecuta en cada tick del contador
             * @param l variable de tipo long que va cambiando en cada tick del contador
             * @return void
             */
            public void onTick(final long l) {
                cont=(l/1000);
                //Guardamos en la variable cont el tiempo en cada cambio del contador
                Log.i("Tiempo",cont+"");
            }

            @Override
            /**
             * Metodo que se ejecuta cuadno finaliza el contador
             * @return void
             */
            public void onFinish() {
                //Metodo que se ejecuta cuando finaliza el tiempo
                cont = 0;
                int actual = preferences.getInt("puntuaciones_size", 0);
                editor = preferences.edit();
                editor.putInt("puntuaciones_size", actual+1);
                String key = "puntuacion_" + (actual+1);

            }
        };


    }


    public int onTouchEvent(MotionEvent event) {
        inicial=false;

        //Meter una booleana para que al pulsar no se esté reiniciando el contador
        contador.start();
        //Al pulsar en una pregunta el tiempo se tiene que parar
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