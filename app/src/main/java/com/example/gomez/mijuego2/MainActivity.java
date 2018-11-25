package com.example.gomez.mijuego2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnJugar;
    Button btnIntroducir;
    Button btnOpciones;
    Button btnCreditos;
    Button btnAyuda;
    Animation animacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configurarLayout();
        iniciarAnimaciones();
    }

    private void configurarLayout(){
        animacion = AnimationUtils.loadAnimation(this, R.anim.boton);
        btnJugar = (Button) findViewById(R.id.jugar);
        btnIntroducir = (Button) findViewById(R.id.introducir);
        btnOpciones = (Button) findViewById(R.id.opciones);
        btnCreditos = (Button) findViewById(R.id.creditos);
        btnAyuda = (Button) findViewById(R.id.ayuda);


        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                switch (view.getId()){
                    case R.id.jugar: intent = new Intent(MainActivity.this, JugarActivity.class);
                        break;
                    case R.id.introducir: intent = new Intent(MainActivity.this, EditarJuegoActivity.class);
                        break;
                    case R.id.opciones: intent = new Intent(MainActivity.this, OpcionesActivity.class);
                        break;
                    case R.id.creditos: intent = new Intent(MainActivity.this, CreditosActivity.class);
                        break;
                    case R.id.ayuda: intent = new Intent(MainActivity.this, AyudaActivity.class);
                        break;
                }

                if (intent!=null) startActivity(intent);
            }
        };

        btnJugar.setOnClickListener(clickListener);
        btnIntroducir.setOnClickListener(clickListener);
        btnOpciones.setOnClickListener(clickListener);
        btnCreditos.setOnClickListener(clickListener);
        btnAyuda.setOnClickListener(clickListener);
    }

    private void iniciarAnimaciones(){
        btnJugar.startAnimation(animacion);
        btnIntroducir.startAnimation(animacion);
        btnOpciones.startAnimation(animacion);
        btnCreditos.startAnimation(animacion);
        btnAyuda.startAnimation(animacion);
    }

}
