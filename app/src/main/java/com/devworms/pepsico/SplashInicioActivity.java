package com.devworms.pepsico;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.vuforia.samples.VuforiaSamples.R;

public class SplashInicioActivity extends Activity {

    //  Preferencias
    SharedPreferences misPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_inicio);


        misPrefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = misPrefs.edit();
        //editor.putBoolean("acceso", false);
        //editor.commit();


        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    boolean acceso = misPrefs.getBoolean("acceso", false); // segundo parametro es el que toma default

                    if(acceso)
                    {
                        Intent intent = new Intent(SplashInicioActivity.this,MenuPepsico.class);
                        startActivity(intent);
                        finish();
                    } else {

                        Intent intent = new Intent(SplashInicioActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
