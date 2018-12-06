package com.example.gomez.mijuego2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class CreditosActivity extends Activity {

    TextView titulo;
    TextView  creditos1;
    TextView  creditos2;
    TextView  creditos3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);
        configurarLayout();
    }

    private void configurarLayout(){
        titulo= (TextView) findViewById(R.id.titulo);
        creditos1= (TextView) findViewById(R.id.ayuda1);
        creditos2= (TextView) findViewById(R.id.ayuda2);
        creditos3= (TextView) findViewById(R.id.ayuda3);
    }
}
