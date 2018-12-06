package com.example.gomez.mijuego2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class IntroducirActivity extends Activity {

    EditText etPregunta;
    EditText etRespuesta1;
    EditText etRespuesta2;
    EditText etRespuesta3;
    EditText etRespuesta4;
    RadioButton correcta1;
    RadioButton correcta2;
    RadioButton correcta3;
    RadioButton correcta4;
    ArrayList<RadioButton> radioButtons = new ArrayList<>();
    Button btnGuardar;
    BD baseDatos;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir);

        baseDatos = new BD(this);
        configurarLayout();
    }

    private void configurarLayout(){
        etPregunta = (EditText) findViewById(R.id.pregunta);
        etRespuesta1 = (EditText) findViewById(R.id.respuesta1);
        etRespuesta2 = (EditText) findViewById(R.id.respuesta2);
        etRespuesta3 = (EditText) findViewById(R.id.respuesta3);
        etRespuesta4 = (EditText) findViewById(R.id.respuesta4);
        correcta1 = (RadioButton) findViewById(R.id.correcta1);
        correcta2 = (RadioButton) findViewById(R.id.correcta2);
        correcta3 = (RadioButton) findViewById(R.id.correcta3);
        correcta4 = (RadioButton) findViewById(R.id.correcta4);
        btnGuardar = (Button) findViewById(R.id.guardar);
        radioButtons.add(correcta1);
        radioButtons.add(correcta2);
        radioButtons.add(correcta3);
        radioButtons.add(correcta4);

        for(RadioButton rb : radioButtons){
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activarBoton((RadioButton) view);
                }
            });
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarTextos()){
                    if(getCorrecta() != -1) {
                        ArrayList<String> respuestas = new ArrayList<>();
                        respuestas.add(etRespuesta1.getText().toString());
                        respuestas.add(etRespuesta2.getText().toString());
                        respuestas.add(etRespuesta3.getText().toString());
                        respuestas.add(etRespuesta4.getText().toString());
                        baseDatos.introducirPregunta(etPregunta.getText().toString(), respuestas, getCorrecta());
                        Toast.makeText(IntroducirActivity.this, "Pregunta introducida con éxito", Toast.LENGTH_SHORT).show();
                        IntroducirActivity.this.finish();
                    }else{
                        Toast.makeText(IntroducirActivity.this, "Error! Por favor, selecciona una respuesta como correcta!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(IntroducirActivity.this, "Error! Por favor, revisa todos los campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void activarBoton(RadioButton boton){
        for(RadioButton btn : radioButtons){
            btn.setChecked(false);
        }
        boton.setChecked(true);
    }

    private int getCorrecta(){
        for(int i=0; i<radioButtons.size(); i++){
            if(radioButtons.get(i).isChecked()) return i+1;
        }
        return -1;
    }

    private boolean validarTextos(){
        String pregunta = etPregunta.getText().toString();
        String respuesta1 = etRespuesta1.getText().toString();
        String respuesta2 = etRespuesta2.getText().toString();
        String respuesta3 = etRespuesta3.getText().toString();
        String respuesta4 = etRespuesta4.getText().toString();

        return pregunta.length() > 0 && respuesta1.length() > 0 && respuesta2.length() > 0 &&
                respuesta3.length() > 0 && respuesta4.length() > 0 ;
    }
}
