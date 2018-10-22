package com.example.gomez.mijuego2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Escena1 extends Escena {

    int efecto;
    Rect btnJugar, btnOpciones, btnAyuda, btnPuntuaciones, btnCreditos;
    Paint rect, letras;
    Bitmap fondo;


    public Escena1(int numEscena, Context context, int colorFondo, int anchoPantalla, int altoPantalla) {
        super(numEscena, context, colorFondo, anchoPantalla, altoPantalla);
        efecto = sonidos.load(context, R.raw.woosh, 1);
        btnJugar = new Rect(anchoPantalla * 30 / 100, altoPantalla * 15 / 100, anchoPantalla * 70 / 100, altoPantalla * 25 / 100);
        btnOpciones = new Rect(anchoPantalla * 30 / 100, altoPantalla * 32 / 100, anchoPantalla * 70 / 100, altoPantalla * 42 / 100);
        btnPuntuaciones = new Rect(anchoPantalla*20/100,altoPantalla*50/100,anchoPantalla*80/100,altoPantalla*60/100);
        btnCreditos = new Rect(anchoPantalla*30/100,altoPantalla*68/100,anchoPantalla*70/100,altoPantalla*78/100);
        btnAyuda = new Rect(anchoPantalla*30/100,altoPantalla*85/100,anchoPantalla*70/100,altoPantalla*95/100);

        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.nuevomenu);

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
        c.drawBitmap(super.escala(R.drawable.nuevomenu,anchoPantalla,altoPantalla), 0, 0, null);
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
        int accion=event.getAction();
        int y = (int) event.getY();
        int x = (int) event.getX();
        switch (accion){
            case MotionEvent.ACTION_DOWN:
                if(btnJugar.contains(x,y))
                    return  2;
                break;
        }
        return numEscena;
    }
}
