package com.devworms.pepsico;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vuforia.samples.VuforiaSamples.R;

public class Rally extends Activity {

    //  Preferencias
    SharedPreferences misPrefs;
    int MI_RESULT = 100;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rally);

        misPrefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        btn1 = (Button)findViewById(R.id.uno);
        btn2 = (Button)findViewById(R.id.dos);
        btn3 = (Button)findViewById(R.id.tres);
        btn4 = (Button)findViewById(R.id.cuatro);
        btn5 = (Button)findViewById(R.id.cinco);
        btn6 = (Button)findViewById(R.id.seis);
        btn7 = (Button)findViewById(R.id.siete);
        btn8 = (Button)findViewById(R.id.ocho);
        btn9 = (Button)findViewById(R.id.nueve);

        reload();
    }

    public void rallyCode(String botonStr){
        Intent i = new Intent(this, RallyCode.class);
        i.putExtra("boton", botonStr);
        //para que te regrese de la actividad RallyCode
        startActivityForResult(i, MI_RESULT);
    }

    public void pistaUno(View v){
        rallyCode("1");
    }

    public void pistaDos(View v){
        rallyCode("2");
    }

    public void pistaTres(View v){
        rallyCode("3");
    }

    public void pistaCuatro(View v){
        rallyCode("4");
    }

    public void pistaCinco(View v){
        rallyCode("5");
    }

    public void pistaSeis(View v){
        rallyCode("6");
    }

    public void pistaSiete(View v){
        rallyCode("7");
    }

    public void pistaOcho(View v){
        rallyCode("8");
    }

    public void pistaNueve(View v){
        rallyCode("9");
    }

    public void reload() {
        btn1.setEnabled(misPrefs.getBoolean("1", true));
        btn2.setEnabled(misPrefs.getBoolean("2", true));
        btn3.setEnabled(misPrefs.getBoolean("3", true));
        btn4.setEnabled(misPrefs.getBoolean("4", true));
        btn5.setEnabled(misPrefs.getBoolean("5", true));
        btn6.setEnabled(misPrefs.getBoolean("6", true));
        btn7.setEnabled(misPrefs.getBoolean("7", true));
        btn8.setEnabled(misPrefs.getBoolean("8", true));
        btn9.setEnabled(misPrefs.getBoolean("9", true));
    }

    //para que te regrese de la actividad RallyCode
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MI_RESULT){
            reload();
        }
    }
}
