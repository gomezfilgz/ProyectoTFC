package com.example.gomez.mijuego2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class AyudaActivity extends Activity {

    TextView titulo;
    TextView  ayuda1;
    TextView  ayuda2;
    TextView  ayuda3;
    TextView  ayuda4;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
        configurarLayout();
    }

    private void configurarLayout(){
        titulo= (TextView) findViewById(R.id.titulo);
        ayuda1= (TextView) findViewById(R.id.ayuda1);
        ayuda2= (TextView) findViewById(R.id.ayuda2);
        ayuda3= (TextView) findViewById(R.id.ayuda3);
        ayuda4= (TextView) findViewById(R.id.ayuda4);
    }



}

