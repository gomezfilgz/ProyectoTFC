
package com.example.gomez.mijuego2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JugarActivity extends Activity {

    TextView tvPregunta;
    TextView tvAciertos;
    TextView tvContador;
    Button btnRespuesta1;
    Button btnRespuesta2;
    Button btnRespuesta3;
    Button btnRespuesta4;

    String pregunta;
    ArrayList<String> respuestas;
    String respuestaCorrecta;
    List<Integer> indicesPreguntas;

    BD baseDatos;
    int fase = 0;
    int aciertos = 0;

    CountDownTimer contador;
    long tiempoFase = 10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);

        baseDatos = new BD(this);
        int numeroPreguntas = baseDatos.numeroPreguntas();
        indicesPreguntas = new ArrayList<>();
        for(int i=1; i<=numeroPreguntas; i++){
            indicesPreguntas.add(i);
        }

        configurarLayout();
        desordenarSiEsNecesario();
        rellenarFase();
    }

    private void configurarLayout(){
        tvPregunta = (TextView) findViewById(R.id.pregunta);
        tvContador = (TextView) findViewById(R.id.contador);
        tvAciertos = (TextView) findViewById(R.id.aciertos);
        btnRespuesta1 = (Button) findViewById(R.id.respuesta1);
        btnRespuesta2 = (Button) findViewById(R.id.respuesta2);
        btnRespuesta3 = (Button) findViewById(R.id.respuesta3);
        btnRespuesta4 = (Button) findViewById(R.id.respuesta4);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("XXX", "Click");
                contador.cancel();
                habilitarBotones(false);
                switch (view.getId()){
                    case R.id.respuesta1: comprobarRespuesta(0, view);
                        break;
                    case R.id.respuesta2: comprobarRespuesta(1, view);
                        break;
                    case R.id.respuesta3: comprobarRespuesta(2, view);
                        break;
                    case R.id.respuesta4: comprobarRespuesta(3, view);
                        break;
                }
            }
        };

        btnRespuesta1.setOnClickListener(clickListener);
        btnRespuesta2.setOnClickListener(clickListener);
        btnRespuesta3.setOnClickListener(clickListener);
        btnRespuesta4.setOnClickListener(clickListener);
    }

    private void desordenarSiEsNecesario(){
        Log.i("XXX", "NORMAL");
        for(int i : indicesPreguntas){
            Log.i("XXX", "> " + i);
        }

        if(SharedPreferencesHelper.recuperarRandom(this)){
            Collections.shuffle(indicesPreguntas);

            Log.i("XXX", "DESPUES");
            for(int i : indicesPreguntas){
                Log.i("XXX", "> " + i);
            }
        }
    }

    private void rellenarFase(){
        if(fase<indicesPreguntas.size()){
            pregunta = baseDatos.getPregunta(indicesPreguntas.get(fase));
            respuestas = baseDatos.getRespuestas(indicesPreguntas.get(fase));
            respuestaCorrecta = baseDatos.getRespuestaCorrecta(indicesPreguntas.get(fase));

            habilitarBotones(true);
            tvPregunta.setText(pregunta);
            btnRespuesta1.setText(respuestas.get(0));
            btnRespuesta2.setText(respuestas.get(1));
            btnRespuesta3.setText(respuestas.get(2));
            btnRespuesta4.setText(respuestas.get(3));
            tvAciertos.setText("" + aciertos);

            btnRespuesta1.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
            btnRespuesta2.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
            btnRespuesta3.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
            btnRespuesta4.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));

            btnRespuesta1.setTextColor(getResources().getColor(android.R.color.black));
            btnRespuesta2.setTextColor(getResources().getColor(android.R.color.black));
            btnRespuesta3.setTextColor(getResources().getColor(android.R.color.black));
            btnRespuesta4.setTextColor(getResources().getColor(android.R.color.black));

            int tiempoMilis = SharedPreferencesHelper.recuperarTiempo(this) * 1000;

            contador = new CountDownTimer(tiempoMilis, 200) {
                @Override
                public void onTick(final long l) {
                    tiempoFase = (l / 1000);
                    tvContador.setText("" + tiempoFase);
                }

                @Override
                public void onFinish() {
                    resolver();
                }
            }.start();

        }else{
            finalizar();
        }
    }

    private void comprobarRespuesta(int indice, View view){
        Button boton = (Button) view;
        if(respuestas.get(indice).equals(respuestaCorrecta)){
            boton.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_light));
            aciertos++;
        }else{
            boton.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_light));
            if(SharedPreferencesHelper.recuperarFinTrasError(this)) finalizar();
        }
        boton.setTextColor(getResources().getColor(android.R.color.white));
        esperarYAvanzar();
    }

    private void resolver(){
        contador.cancel();
        habilitarBotones(false);
        resolverBoton(btnRespuesta1);
        resolverBoton(btnRespuesta2);
        resolverBoton(btnRespuesta3);
        resolverBoton(btnRespuesta4);
        esperarYAvanzar();
    }

    private void resolverBoton(Button boton){
        if(boton.getText().toString().equals(respuestaCorrecta)){
            boton.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_light));
            boton.setTextColor(getResources().getColor(android.R.color.white));
        }
    }

    private void esperarYAvanzar(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fase++;
                rellenarFase();
            }
        }, 2000);
    }

    private void finalizar(){
        Intent intent = new Intent(this, ResumenActivity.class);
        intent.putExtra("PUNTOS", aciertos);
        startActivity(intent);
        finish();
    }

    private void habilitarBotones(boolean habilitado) {
        btnRespuesta1.setEnabled(habilitado);
        btnRespuesta2.setEnabled(habilitado);
        btnRespuesta3.setEnabled(habilitado);
        btnRespuesta4.setEnabled(habilitado);
    }

}

