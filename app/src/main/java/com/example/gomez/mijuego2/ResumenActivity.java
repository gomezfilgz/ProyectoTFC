package com.example.gomez.mijuego2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResumenActivity extends Activity {

    TextView resumen;
    Button compartir;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        configurarLayout();
    }

    private void configurarLayout(){
        int puntos = getIntent().getExtras().getInt("PUNTOS");
        resumen = (TextView) findViewById(R.id.resumen);
        resumen.setText(getString(R.string.Felicidades, puntos));

        compartir = (Button) findViewById(R.id.compartir);
        compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,"Mira la puntuación que he obtenido en Guitar Quiz!");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Guitar Quiz");
                startActivity(Intent.createChooser(shareIntent, "Compartir..."));
            }
        });
    }



}
