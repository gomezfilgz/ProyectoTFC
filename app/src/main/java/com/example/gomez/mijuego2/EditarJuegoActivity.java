package com.example.gomez.mijuego2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class EditarJuegoActivity extends Activity {

    int INTRODUCIR_REQUEST_CODE = 10;

    RecyclerView listado;
    PreguntasAdapter adaptador;
    FloatingActionButton button;
    BD baseDatos;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        baseDatos = new BD(this);
        configurarLayout();
    }

    private void configurarLayout(){
        listado = (RecyclerView) findViewById(R.id.lista);
        adaptador = new PreguntasAdapter(baseDatos);
        listado.setAdapter(adaptador);

        button = (FloatingActionButton) findViewById(R.id.nueva);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(EditarJuegoActivity.this, IntroducirActivity.class), INTRODUCIR_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == INTRODUCIR_REQUEST_CODE){
            adaptador.actualizar();
        }
    }
}
