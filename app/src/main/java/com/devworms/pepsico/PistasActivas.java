package com.devworms.pepsico;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vuforia.samples.VuforiaSamples.R;
import com.vuforia.samples.VuforiaSamples.ui.ImageTargets.ImageTargets;

public class PistasActivas extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pistas_activas);
    }

    public void vuforia(View v){
        Intent i = new Intent(this, ImageTargets.class);
        startActivity(i);
    }
}
