package com.devworms.pepsico;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vuforia.samples.VuforiaSamples.R;


public class MenuPepsico extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pepsico);
    }

    public void Agenda(View v){
        Intent i = new Intent(this, agendaActivity.class);
        startActivity(i);

    }

    public void Rally(View v){
        Intent i = new Intent(this, Rally.class);
        startActivity(i);

    }

    public void PistasActivas(View v){
        Intent i = new Intent(this, PistasActivas.class);
        startActivity(i);
    }
}
