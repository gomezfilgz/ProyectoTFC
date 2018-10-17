package com.example.gomez.mijuego2;

import android.content.Context;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.view.MotionEvent;

/**
 * Created by gomez on 15/02/2018.
 */

public class Escena2 extends Escena {
    int efecto;


    public Escena2(int numEscena, Context context, int colorFondo, int anchoPantalla, int altoPantalla) {
        super(numEscena, context, colorFondo, anchoPantalla, altoPantalla);
        efecto=sonidos.load(context,R.raw.woosh,1);
    }

    public void dibujar(Canvas c){
        super.dibujar(c);

    }
    public void actualizarFisica(){
        super.actualizarFisica();

    }
    public int onTouchEvent(MotionEvent event) {
        int nuevaEscena=super.onTouchEvent(event);
        int v= audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        sonidos.play(efecto,v,v,1,1,1);

        int accion=event.getAction();
        switch (accion) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
//                int v= audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

                break;
        }


        if (nuevaEscena!=numEscena) return nuevaEscena;
        return numEscena;
    }
}