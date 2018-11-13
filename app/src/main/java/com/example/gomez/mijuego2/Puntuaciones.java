package com.example.gomez.mijuego2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by gomez on 13/11/2018.
 */

public class Puntuaciones extends Escena {

    Bitmap fondo, btnBack;
    Rect btnVolver;
    Paint rect, letras;


    public Puntuaciones(int numEscena, Context context, int colorFondo, int anchoPantalla, int altoPantalla) {
        super(numEscena, context, colorFondo, anchoPantalla, altoPantalla);
        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.guitarmastil);
        btnVolver = new Rect(anchoPantalla * 5 / 100, altoPantalla * 15 / 100, anchoPantalla * 20 / 100, altoPantalla * 30 / 100);

        btnBack = super.escala(R.drawable.back, 50, 50);

        rect = new Paint();
        rect.setColor(Color.BLACK);
        rect.setAlpha(200);

        letras = new Paint();
        letras.setAlpha(255);
        letras.setColor(Color.BLACK);
        letras.setTextSize(getPixels(30));



        btnVolver = new Rect(anchoPantalla * 10 / 100, altoPantalla * 10 / 100, anchoPantalla * 25 / 100, altoPantalla * 20 / 100);

    }

    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawBitmap(super.escala(R.drawable.guitarmastil, anchoPantalla, altoPantalla), 0, 0, null);
        c.drawText("PUNTUACIONES", 38, 50, letraslogo);
        c.drawRect(btnVolver, rect);
        c.drawBitmap(btnBack, anchoPantalla * 10 / 100, altoPantalla * 10 / 100, null);

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
                break;
        }
        return numEscena;
    }
}