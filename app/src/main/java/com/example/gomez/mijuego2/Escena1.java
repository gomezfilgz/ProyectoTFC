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
 * Created by gomez on 15/02/2018.
 */

public class Escena1 extends Escena {

    int efecto;
    Rect btnJugar, btnOpciones, btnAyuda, btnPuntuaciones, btnCreditos;
    Paint rect, letras;
    Bitmap mole;


    public Escena1(int numEscena, Context context, int colorFondo, int anchoPantalla, int altoPantalla) {
        super(numEscena, context, colorFondo, anchoPantalla, altoPantalla);
        efecto = sonidos.load(context, R.raw.woosh, 1);
        btnJugar = new Rect(anchoPantalla * 30 / 100, altoPantalla * 15 / 100, anchoPantalla * 70 / 100, altoPantalla * 25 / 100);
        btnOpciones = new Rect(anchoPantalla * 30 / 100, altoPantalla * 32 / 100, anchoPantalla * 70 / 100, altoPantalla * 42 / 100);
        btnPuntuaciones = new Rect(anchoPantalla*20/100,altoPantalla*50/100,anchoPantalla*80/100,altoPantalla*60/100);
        btnCreditos = new Rect(anchoPantalla*30/100,altoPantalla*68/100,anchoPantalla*70/100,altoPantalla*78/100);
        btnAyuda = new Rect(anchoPantalla*30/100,altoPantalla*85/100,anchoPantalla*70/100,altoPantalla*95/100);

        mole = BitmapFactory.decodeResource(context.getResources(), R.drawable.cerdo);

        rect = new Paint();
        rect.setColor(Color.WHITE);
        rect.setAlpha(200);
        letras = new Paint();
        letras.setAlpha(255);
        letras.setColor(Color.BLACK);
        letras.setTextSize(25);

    }

    public void dibujar(Canvas c) {

        super.dibujar(c);
        c.drawBitmap(mole, 20, 20, null);
        c.drawRect(btnJugar, rect);
        c.drawText("Jugar", 120, 100, letras);
        c.drawRect(btnOpciones, rect);
        c.drawText("Opciones", 112, 180, letras);
        c.drawRect(btnPuntuaciones,rect);
        c.drawText("Puntuaciones", 90, 260, letras);
        c.drawRect(btnCreditos,rect);
        c.drawText("Creditos", 116, 340, letras);
        c.drawRect(btnAyuda,rect);
        c.drawText("Ayuda", 120, 420, letras);


    }
    public void actualizarFisica(){
        super.actualizarFisica();

    }
    public int onTouchEvent(MotionEvent event) {
        int nuevaEscena=super.onTouchEvent(event);
        if (nuevaEscena!=numEscena) return nuevaEscena;
        return numEscena;
    }
}