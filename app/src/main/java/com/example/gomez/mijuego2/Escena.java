package com.example.gomez.mijuego2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;

/**
 * Created by gomez on 15/02/2018.
 */

public class Escena {
    SoundPool sonidos;
    final private int maxSonidosSimultaneos=10;
    ;
    int numEscena;
    Context context;
    int colorFondo;
    Paint pTexto,pBoton;
    int anchoPantalla, altoPantalla;
    Rect bAnt, bSig;
    AudioManager audioManager;

    public Escena(int numEscena, Context context, int colorFondo, int anchoPantalla, int altoPantalla) {
        this.numEscena=numEscena;
        this.context=context;
        this.colorFondo=colorFondo;
        this.anchoPantalla=anchoPantalla;
        this.altoPantalla=altoPantalla;
        audioManager=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            SoundPool.Builder spb=new SoundPool.Builder();
            spb.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA) .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build());
            spb.setMaxStreams(maxSonidosSimultaneos);
            this.sonidos=spb.build();
        } else
        {
            this.sonidos=new SoundPool(maxSonidosSimultaneos, AudioManager.STREAM_MUSIC, 0);
        }




        pTexto=new Paint();
        pTexto.setColor(Color.RED);
        pTexto.setTextSize(getPixels(50));
        pBoton=new Paint();
        pBoton.setColor(Color.WHITE);
        pBoton.setAlpha(200);
        bAnt=new Rect(0,altoPantalla-getPixels(50),getPixels(50),altoPantalla);
        bSig=new Rect(anchoPantalla-getPixels(50),altoPantalla-getPixels(50),anchoPantalla,altoPantalla);
    }

    public void dibujar(Canvas c){
        c.drawColor(colorFondo);
        if (numEscena<5) c.drawRect(bSig,pBoton);
        if (numEscena>1) c.drawRect(bAnt,pBoton);

    }

    public int getNumEscena() {
        return numEscena;
    }
    public void actualizarFisica(){

    }
    public int onTouchEvent(MotionEvent event) {
        int accion=event.getAction();
        switch (accion) {

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (bAnt.contains((int)event.getX(),(int)event.getY()) && numEscena>1)
                    return (numEscena-1);
                else if (bSig.contains((int)event.getX(),(int)event.getY()) && numEscena<5)
                    return (numEscena+1);




        }

        return numEscena;
    }

    public Bitmap escalaAnchura(int res, int nuevoAncho) {
        Bitmap bitmapAux= BitmapFactory.decodeResource(context.getResources(), res); if (nuevoAncho==bitmapAux.getWidth()) return bitmapAux; return bitmapAux.createScaledBitmap(bitmapAux, nuevoAncho, (bitmapAux.getHeight() * nuevoAncho) / bitmapAux.getWidth(),true); }

    public Bitmap escalaAltura(int res, int nuevoAlto ) {
        Bitmap bitmapAux=BitmapFactory.decodeResource(context.getResources(), res); if (nuevoAlto==bitmapAux.getHeight()) return bitmapAux; return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) / bitmapAux.getHeight(), nuevoAlto, true); }

    int getPixels(float dp) {
        DisplayMetrics metrics = new DisplayMetrics(); ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(). getMetrics(metrics); return (int)(dp*metrics.density); }

}
