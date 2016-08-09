package com.devworms.pepsico;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.vuforia.samples.VuforiaSamples.R;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener  {

    // Progress Dialog
    private ProgressDialog pDialog;

    //  Datos Usuario
    EditText nombre;
    EditText paterno;
    EditText materno;
    EditText mail;
    EditText contrasena;
    EditText repiteContrasena;

    Spinner tipo;

    //  Preferencias
    SharedPreferences misPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //  Datos Usuario
        nombre = (EditText)findViewById(R.id.nombreText);
        paterno = (EditText)findViewById(R.id.apellidoPText);
        materno = (EditText)findViewById(R.id.apellidoMText);
        mail = (EditText)findViewById(R.id.mailText);
        tipo = (Spinner) findViewById(R.id.tipoSpinner);
        contrasena = (EditText)findViewById(R.id.passText);
        repiteContrasena = (EditText)findViewById(R.id.cnfPassText);

        misPrefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        //  SPINNER TIPO
            Spinner spinnerArea = (Spinner) findViewById(R.id.tipoSpinner);

            ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(this,
                    R.array.daimler_tipo_array, android.R.layout.simple_spinner_item);

            adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerArea.setAdapter(adapterTipo);

            spinnerArea.setOnItemSelectedListener(this);

    }

    public void moduloRegistro (View view){
        String pass = contrasena.getText().toString();
        String rePass = repiteContrasena.getText().toString();
        String email = mail.getText().toString();
        String nombreString = nombre.getText().toString();

        if(pass.trim().length() == 0 || rePass.trim().length() == 0 ||
                email.trim().length() == 0 || nombreString.trim().length() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Faltan campos", Toast.LENGTH_SHORT);
            toast.show();
        }

        else {

            if (pass.equals(rePass)) {
                new LoadAlbums().execute();
            } else {

                Context context = getApplicationContext();
                CharSequence text = "Las contraseñas no coinciden";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        /*
            if(position == 0)    {
                puesto.setEnabled(true);
                empresa.setEnabled(true);
            }
            else {
                puesto.setEnabled(false);
                empresa.setEnabled(false);
            }
        */
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    class LoadAlbums extends AsyncTask<String, String, String> {


        String nombreStr = nombre.getText().toString();
        String patStr = paterno.getText().toString();
        String matStr = materno.getText().toString();
        String mailStr = mail.getText().toString();
        String tipoStr = tipo.getSelectedItem().toString();
        String passStr = contrasena.getText().toString();

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Registrando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting Albums JSON
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "nombre=" + nombreStr + "&paterno=" + patStr + "&materno=" + matStr +
                    "&correo=" + mailStr + "&area=" + tipoStr + "&passw=" + passStr);
            Request request = new Request.Builder()
                    .url("http://app-pepsico.palindromo.com.mx/APP/registro.php")
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all albums

            pDialog.dismiss();

            SharedPreferences.Editor editor = misPrefs.edit();
            editor.putString("email", mailStr);
            editor.putBoolean("acceso", true);
            editor.commit();

            Intent registrarScreen = new Intent(MainActivity.this, RegistroExito.class);
            startActivity(registrarScreen);

            finishAffinity();
        }

    }

}