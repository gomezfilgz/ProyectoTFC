package com.example.gomez.mijuego2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by gomez on 05/11/2018.
 */

public class Opciones extends Escena {

    Bitmap fondo, btnBack;
    Rect btnVolver,btntiempo20,btntiempo30;
    Paint rect, letras;
    int tiempo_respuesta;


    public Opciones(int numEscena, Context context, int colorFondo, int anchoPantalla, int altoPantalla) {
        super(numEscena, context, colorFondo, anchoPantalla, altoPantalla);
        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.guitarmastil);
        editor = preferences.edit();
        btnVolver = new Rect(anchoPantalla * 5 / 100, altoPantalla * 15 / 100, anchoPantalla * 20 / 100, altoPantalla * 30 / 100);

        btnBack = super.escala(R.drawable.back, 50, 50);

        rect = new Paint();
        rect.setColor(Color.BLACK);
        rect.setAlpha(0);

        letras = new Paint();
        letras.setAlpha(255);
        letras.setColor(Color.MAGENTA);
        letras.setTextSize(getPixels(30));

        btnVolver = new Rect(anchoPantalla * 10 / 100, altoPantalla * 10 / 100, anchoPantalla * 25 / 100, altoPantalla * 20 / 100);
        btntiempo20 = new Rect(anchoPantalla * 10 / 100, altoPantalla * 60 / 100, anchoPantalla * 45 / 100, altoPantalla * 80 / 100);
        btntiempo30 = new Rect(anchoPantalla * 60 / 100, altoPantalla * 60 / 100, anchoPantalla * 90 / 100, altoPantalla * 80 / 100);

    }

    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawBitmap(super.escala(R.drawable.guitarmastil, anchoPantalla, altoPantalla), 0, 0, null);
        c.drawText("OPCIONES", 60, 50, letraslogo);
        c.drawText("Selecciona el tiempo", 25, 180, letras);
        c.drawText("para responder", 50, 210, letras);
        c.drawRect(btnVolver, rect);
        c.drawRect(btntiempo20, rect);
        c.drawRect(btntiempo30, rect);
        c.drawText("20S", 50, 320, letraslogo);
        c.drawText("30S", 195, 320, letraslogo);
        c.drawBitmap(btnBack, anchoPantalla * 10 / 100, altoPantalla * 15 / 100, null);

    }

    public void actualizarFisica() {
        super.actualizarFisica();
    }


    public int onTouchEvent(MotionEvent event) {
        int accion = event.getAction();
        int y = (int) event.getY();
        int x = (int) event.getX();
        switch (accion) {
            case MotionEvent.ACTION_DOWN:
                if (btnVolver.contains(x, y)){
                    return 1;
                }
                if (btntiempo20.contains(x, y)){
                    tiempo_respuesta=20;
                    editor.putInt("Tiempo de respuesta",tiempo_respuesta);
                    editor.commit();
                }
                if (btntiempo30.contains(x, y)){
                    tiempo_respuesta=30;
                    editor.putInt("Tiempo de respuesta",tiempo_respuesta);
                    editor.commit();
                }
                break;
        }
        return numEscena;
    }
}