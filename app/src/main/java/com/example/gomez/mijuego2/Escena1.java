package com.example.gomez.mijuego2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;

public class Escena1 extends Escena {

    int efecto;
    Rect btnJugar, btnOpciones,btnPuntuaciones, btnCreditos;
    Paint rect, letras;
    Bitmap fondo;


    public Escena1(int numEscena, Context context, int colorFondo, int anchoPantalla, int altoPantalla) {
        super(numEscena, context, colorFondo, anchoPantalla, altoPantalla);
        efecto = sonidos.load(context, R.raw.woosh, 1);
        btnJugar = new Rect(anchoPantalla * 30 / 100, altoPantalla * 20 / 100, anchoPantalla * 70 / 100, altoPantalla * 30 / 100);
        btnOpciones = new Rect(anchoPantalla * 30 / 100, altoPantalla * 40 / 100, anchoPantalla * 70 / 100, altoPantalla * 50 / 100);
        btnPuntuaciones = new Rect(anchoPantalla*20/100,altoPantalla*60/100,anchoPantalla*80/100,altoPantalla*70/100);
        btnCreditos = new Rect(anchoPantalla*30/100,altoPantalla*80/100,anchoPantalla*70/100,altoPantalla*90/100);

        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.guitarplay);

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
        c.drawBitmap(super.escala(R.drawable.guitarplay,anchoPantalla,altoPantalla), 0, 0, null);
        c.drawText("GUITAR-QUIZ", 38, 50, letraslogo);
        c.drawRect(btnJugar, rect);
        c.drawText("Jugar", 120, 125, letras);
        c.drawRect(btnOpciones, rect);
        c.drawText("Opciones", 112, 220, letras);
        c.drawRect(btnPuntuaciones,rect);
        c.drawText("Puntuaciones", 90, 320, letras);
        c.drawRect(btnCreditos,rect);
        c.drawText("Creditos", 116, 410, letras);

    }
    public void actualizarFisica(){
        super.actualizarFisica();

    }
    public int onTouchEvent(MotionEvent event) {
        int accion=event.getAction();
        int y = (int) event.getY();
        int x = (int) event.getX();
        switch (accion){
            case MotionEvent.ACTION_DOWN:
                if(btnJugar.contains(x,y))
                    return  2;
                if(btnOpciones.contains(x,y))
                    return  3;
                if(btnPuntuaciones.contains(x,y)){
                    return 4;
                }
                if(btnCreditos.contains(x,y)){
                    return 5;
                }
                break;
        }
        return numEscena;
    }
}
