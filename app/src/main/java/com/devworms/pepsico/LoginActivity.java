package com.devworms.pepsico;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.vuforia.samples.VuforiaSamples.R;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends Activity {

    // Progress Dialog
    private ProgressDialog pDialog;

    //  Datos Usuario
    EditText mail;
    EditText contrasena;

    //  Preferencias
    SharedPreferences misPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mail = (EditText)findViewById(R.id.mailText);
        contrasena = (EditText)findViewById(R.id.passText);

        misPrefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

    }

    public void pantallaRegistro(View view) {
        Intent llamarScreenCodigo = new Intent(this, MainActivity.class);
        startActivity(llamarScreenCodigo);
    }

    public void moduloLogin(View view){
        new LoadAlbums().execute();
   }


    class LoadAlbums extends AsyncTask<String, String, String> {

        String mailStr = mail.getText().toString();
        String passStr = contrasena.getText().toString();

        Response response;
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Ingresando...");
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
            RequestBody body = RequestBody.create(mediaType, "correo=" + mailStr + "&passw=" + passStr);
            Request request = new Request.Builder()
                    .url("http://app-pepsico.palindromo.com.mx/APP/userLogin.php")
                    .post(body)
                    .build();

            try {
                response = client.newCall(request).execute();

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

            String exito = "\"Success\"";
            String text = null;

            pDialog.dismiss();

            try {
                text = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(exito.equals(text))  {

                SharedPreferences.Editor editor = misPrefs.edit();
                editor.putString("email", mailStr);
                editor.putBoolean("acceso", true);
                editor.commit();

                Intent llamarScreenCodigo = new Intent(LoginActivity.this, MenuPepsico.class);
                startActivity(llamarScreenCodigo);

                finish();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                toast.show();
            }

        }

    }
}