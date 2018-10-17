package com.example.gomez.mijuego2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.view.MotionEvent;

/**
 * Created by gomez on 15/02/2018.
 */

public class Escena2 extends Escena {

Bitmap fondo;
    public Escena2(int numEscena, Context context, int colorFondo, int anchoPantalla, int altoPantalla) {
        super(numEscena, context, colorFondo, anchoPantalla, altoPantalla);
        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.human);
    }

    public void dibujar(Canvas c){
        super.dibujar(c);
        c.drawBitmap(super.escala(R.drawable.human,anchoPantalla,altoPantalla), 0, 0, null);
    }
    public void actualizarFisica(){
        super.actualizarFisica();

    }
    public int onTouchEvent(MotionEvent event) {
        int nuevaEscena=super.onTouchEvent(event);


        int accion=event.getAction();
        switch (accion) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:

                break;
        }
        if (nuevaEscena!=numEscena) return nuevaEscena;
        return numEscena;
    }
}