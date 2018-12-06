package com.example.gomez.mijuego2;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class OpcionesActivity extends Activity {

    SeekBar seekBar;
    TextView tvTiempo;
    CheckBox cbAcabar;
    CheckBox cbRandom;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        configuraLayout();
    }

    private void configuraLayout(){
        seekBar = (SeekBar) findViewById(R.id.duracion);
        tvTiempo = (TextView) findViewById(R.id.tiempo);
        cbAcabar = (CheckBox) findViewById(R.id.acabar);
        cbRandom = (CheckBox) findViewById(R.id.random);
        seekBar.setProgress(SharedPreferencesHelper.recuperarTiempo(this));
        tvTiempo.setText(""+SharedPreferencesHelper.recuperarTiempo(this));
        cbAcabar.setChecked(SharedPreferencesHelper.recuperarFinTrasError(this));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvTiempo.setText(""+i);
                SharedPreferencesHelper.guardarTiempo(OpcionesActivity.this, i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.getId()==R.id.random){
                    SharedPreferencesHelper.guardarRandom(OpcionesActivity.this, b);
                }else if(compoundButton.getId()==R.id.acabar) {
                    SharedPreferencesHelper.guardarFinTrasError(OpcionesActivity.this, b);
                }
            }
        };

        cbAcabar.setOnCheckedChangeListener(onCheckedChangeListener);
        cbRandom.setOnCheckedChangeListener(onCheckedChangeListener);
    }
}
