package com.devworms.pepsico;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.vuforia.samples.VuforiaSamples.R;

public class agendaDetalleActivity extends Activity {

    String fechaCompuesta;
    String dia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_detalle);

        String nombre = getIntent().getStringExtra("nombre");
        String salon = getIntent().getStringExtra("salon");
        String horario = getIntent().getStringExtra("horario");
        String codigo = getIntent().getStringExtra("codigo");
        String recomendaciones = getIntent().getStringExtra("recomendaciones");
        String fecha = getIntent().getStringExtra("fecha");

        TextView fechaCTV = (TextView)findViewById(R.id.fechaCompleta);
        TextView nombreTV = (TextView)findViewById(R.id.nombre);
        TextView salonTV = (TextView)findViewById(R.id.lugar);
        TextView horarioTV = (TextView)findViewById(R.id.horario);
        TextView codigoTV = (TextView)findViewById(R.id.recom);

        if(fecha.equals("lunes")){
            fechaCompuesta = "Lunes 22 de Agosto 2016 ";
        dia="dia1";}
        else if(fecha.equals("martes")){
            fechaCompuesta = "Martes 23 de Agosto 2016 ";
        dia="dia2";}
        else if(fecha.equals("miercoles")){
            fechaCompuesta = "Miercoles 24 de Agosto 2016 ";
        dia="dia3";}

        fechaCTV.setText(fechaCompuesta);
        nombreTV.setText(nombre);
        salonTV.setText(salon);
        horarioTV.setText(horario);
        codigoTV.setText(codigo);
    }

}
