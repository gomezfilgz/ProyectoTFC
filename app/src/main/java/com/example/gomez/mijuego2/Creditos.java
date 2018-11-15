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
 * Created by gomez on 14/11/2018.
 */

public class Creditos extends Escena {

    Bitmap fondo, btnBack;
    Rect btnVolver;
    Paint rect, letras;


    public Creditos(int numEscena, Context context, int colorFondo, int anchoPantalla, int altoPantalla) {
        super(numEscena, context, colorFondo, anchoPantalla, altoPantalla);
        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.guitarmastil);
        btnVolver = new Rect(anchoPantalla * 5 / 100, altoPantalla * 15 / 100, anchoPantalla * 20 / 100, altoPantalla * 30 / 100);

        btnBack = super.escala(R.drawable.back, 50, 50);

        rect = new Paint();
        rect.setColor(Color.BLACK);
        rect.setAlpha(0);

        letras = new Paint();
        letras.setAlpha(255);
        letras.setColor(Color.BLACK);
        letras.setTextSize(getPixels(30));

    }

    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawBitmap(super.escala(R.drawable.guitarmastil, anchoPantalla, altoPantalla), 0, 0, null);
        c.drawText(context.getString(R.string.creditos),anchoPantalla*40/100,altoPantalla*20/100,letraslogo);
        c.drawRect(btnVolver, rect);
        c.drawBitmap(btnBack, anchoPantalla * 5 / 100, altoPantalla * 15 / 100, null);


        /*
        //Dibujamos informacion de en que escena nos hayamos
        c.drawText(context.getString(R.string.Imagenes),anchoPantalla*30/100,altoPantalla*20/100,letras);
        c.drawText("Pixabay.com",anchoPantalla*35/100,altoPantalla*26/100,letras3);
        c.drawText(context.getString(R.string.Ayuda),anchoPantalla*35/100,altoPantalla*35/100,letras);
        c.drawText("Javier Conde,Antonio Collazo",anchoPantalla*20/100,altoPantalla*40/100,letras3);
        c.drawText("Christian Rodriguez,Julio Gómez",anchoPantalla*20/100,altoPantalla*47/100,letras3);
        c.drawText(context.getString(R.string.Musica),anchoPantalla*34/100,altoPantalla*55/100,letras);
        c.drawText("Youtube/channel/LegendFromHeaven",anchoPantalla*15/100,altoPantalla*62/100,letras3);
        c.drawText(context.getString(R.string.Fuente),anchoPantalla*34/100,altoPantalla*70/100,letras);
        c.drawText("Dafont.com",anchoPantalla*35/100,altoPantalla*77/100,letras3);
*/
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