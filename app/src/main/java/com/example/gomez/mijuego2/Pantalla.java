package com.example.gomez.mijuego2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

/**
 * Created by gomez on 15/02/2018.
 */

public class Pantalla extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder surfaceHolder;
    Context context;
    boolean funcionando=false;
    Hilo hilo;
    int altoPantalla=0, anchoPantalla=0;
    GestureDetector detectorGestos;
    Bitmap fondo, nave;
    PointF coor;
    PointF pulsacion=null;
    Escena escenaActual;

    public Pantalla(Context context){
        super(context);
        this.context=context;
        this.surfaceHolder = getHolder();       // Se obtiene el holder
        this.surfaceHolder.addCallback(this);   // Se indica donde van las
        hilo=new Hilo();  // funciones callback
        detectorGestos=new GestureDetector(context,new MisGestos());
    }

//    public void dibujar(Canvas c){
//        c.drawBitmap(fondo, 0,0,null);
//        c.drawBitmap(nave, coor.x, coor.y,null);
//    }

//    public void actualizarFisica(){
//        if (pulsacion!=null){
//            if (pulsacion.x > anchoPantalla / 2) {
//                coor.x+=getPixels(3);
//                if (coor.x+nave.getWidth()>anchoPantalla) coor.x=anchoPantalla-nave.getWidth();
//            } else {
//                coor.x-=getPixels(3);
//                if (coor.x<0) coor.x=0;
//
//            }
//        }
//    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int nuevaEscena=escenaActual.onTouchEvent(event);

        if (nuevaEscena!=escenaActual.getNumEscena()){
            switch (nuevaEscena){
                case 1: escenaActual=new Escena1(1, context, Color.RED,anchoPantalla,altoPantalla);
                    break;
                case 2: escenaActual=new Escena2(2, context, Color.GREEN,anchoPantalla,altoPantalla);
                    break;

            }
        }

        return true;
    }

    public Bitmap escalaAnchura(int res, int nuevoAncho) {
        Bitmap bitmapAux= BitmapFactory.decodeResource(context.getResources(), res); if (nuevoAncho==bitmapAux.getWidth()) return bitmapAux; return bitmapAux.createScaledBitmap(bitmapAux, nuevoAncho, (bitmapAux.getHeight() * nuevoAncho) / bitmapAux.getWidth(),true); }
    public Bitmap escalaAltura(int res, int nuevoAlto ) {
        Bitmap bitmapAux=BitmapFactory.decodeResource(context.getResources(), res); if (nuevoAlto==bitmapAux.getHeight()) return bitmapAux; return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) / bitmapAux.getHeight(), nuevoAlto, true); }
    int getPixels(float dp) {
        DisplayMetrics metrics = new DisplayMetrics(); ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(). getMetrics(metrics); return (int)(dp*metrics.density); }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        anchoPantalla=width;
        altoPantalla=height;
        escenaActual=new Escena1(1,context,Color.BLUE,anchoPantalla,altoPantalla);
        if (!funcionando){
//            fondo=BitmapFactory.decodeResource(getResources(),R.drawable.tierraluna);
//            fondo=Bitmap.createScaledBitmap(fondo, anchoPantalla,altoPantalla,false);
//            nave=escalaAltura(R.drawable.nave,getPixels(100));
//            coor=new PointF(anchoPantalla/2-nave.getWidth()/2,altoPantalla-nave.getHeight());
            funcionando=true;
            if (hilo.getState() == Thread.State.NEW) {
                hilo.start(); // si el hilo no ha sido creado se crea;
            }

            if (hilo.getState() == Thread.State.TERMINATED) {      // si el hilo ha sido finalizado se crea de nuevo;
                hilo=new Hilo();
                hilo.start(); // se arranca el hilo
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        funcionando=false;
        try {
            hilo.join();   // Se espera a que finalize
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Hilo extends Thread{
        @Override
        public void run() {
            while (funcionando){
                Canvas c=null;
                try{
                    c=surfaceHolder.lockCanvas();
                    if (c!=null){
                        synchronized (surfaceHolder) {
                            escenaActual.actualizarFisica();
                            escenaActual.dibujar(c);
                        }
                    }
                }finally {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }

    class MisGestos extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("pulso", "  double tab");
            pulsacion=null;
            return true;
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if (velocityX>0) Log.i("pulso", "  fling -->");
            else Log.i("pulso", "  fling <--");

            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            Log.i("pulso", "  long press");
        }
    }


}