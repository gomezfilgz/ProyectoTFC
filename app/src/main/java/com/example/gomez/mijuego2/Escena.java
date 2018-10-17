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
    Paint pBoton,rect;
    int anchoPantalla, altoPantalla;
    Rect bAnt;
    Bitmap back;
    AudioManager audioManager;

    public Escena(int numEscena, Context context, int colorFondo, int anchoPantalla, int altoPantalla) {
        this.numEscena=numEscena;
        this.context=context;
        this.colorFondo=colorFondo;
        this.anchoPantalla=anchoPantalla;
        this.altoPantalla=altoPantalla;
        back = escala(R.drawable.back, 30, 30);
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

        rect = new Paint();
        rect.setAlpha(100);
        rect.setColor(Color.WHITE);
        bAnt = new Rect(anchoPantalla * 85 / 100, altoPantalla * 15 / 100, anchoPantalla * 93 / 100, altoPantalla * 23 / 100);
    }

    public void dibujar(Canvas c){
        c.drawColor(colorFondo);
        if (numEscena>1) c.drawRect(bAnt,pBoton);
        c.drawBitmap(back,anchoPantalla*85/100,altoPantalla*15/100,rect);

    }

    public int getNumEscena() {
        return numEscena;
    }
    public void actualizarFisica(){

    }
    public int onTouchEvent(MotionEvent event) {
        int accion=event.getAction();
        int y = (int) event.getY();
        int x = (int) event.getX();
        switch (accion) {
            case MotionEvent.ACTION_DOWN:
                if (bAnt.contains(x,y) && numEscena>1)
                    return (numEscena-1);
        }
        return numEscena;
    }

    /**
     * Metodo usado para reescalar una imagen
     * @param res recurso que queremos escalar
     * @param nuevoAncho valor de la variable al cual queremos escalar en anchura
     * @param nuevoAlto valor de la variable al cual queremos escalar en altura
     * @return nos devulve el recurso escalado
     */
    public Bitmap escala(int res, int nuevoAncho, int nuevoAlto){
        Bitmap bitmapAux=BitmapFactory.decodeResource(context.getResources(), res);
        return bitmapAux.createScaledBitmap(bitmapAux,nuevoAncho, nuevoAlto, true);
    }

    public Bitmap escalaAnchura(int res, int nuevoAncho) {
        Bitmap bitmapAux= BitmapFactory.decodeResource(context.getResources(), res); if (nuevoAncho==bitmapAux.getWidth()) return bitmapAux; return bitmapAux.createScaledBitmap(bitmapAux, nuevoAncho, (bitmapAux.getHeight() * nuevoAncho) / bitmapAux.getWidth(),true); }

    public Bitmap escalaAltura(int res, int nuevoAlto ) {
        Bitmap bitmapAux=BitmapFactory.decodeResource(context.getResources(), res); if (nuevoAlto==bitmapAux.getHeight()) return bitmapAux; return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) / bitmapAux.getHeight(), nuevoAlto, true); }

    int getPixels(float dp) {
        DisplayMetrics metrics = new DisplayMetrics(); ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(). getMetrics(metrics); return (int)(dp*metrics.density); }

}
