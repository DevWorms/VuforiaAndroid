package com.devworms.pepsico;


import android.os.AsyncTask;
import android.util.Log;

import com.devworms.pepsico.pojo.menuPojo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by sergio on 29/05/16.
 */
public class ApiRest {

    public static List<menuPojo> consultarListadoMenu(String dia) {

        List<menuPojo> lMenu = new ArrayList<menuPojo>();

        try {
            Log.d("RestApi","respuesta  consulta");

            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/octet-stream");
            Request request = new Request.Builder()
                    .url("http://app-pepsico.palindromo.com.mx/APP/"+dia+".php")
                    .get()
                    .build();
            JSONArray values = new RequestApi().execute(request).get();
            Log.d("RestApi","respuesta "+ values.length());
            for (int i = 0; i < values.length(); i++) {

                JSONObject sensorApi = values.getJSONObject(i);
                menuPojo menPojo = new menuPojo();
                Log.d("RestApi","respuesta "+sensorApi.getString("id"));
                menPojo.setId(sensorApi.getString("id"));
                Log.d("RestApi","respuesta "+sensorApi.getString("nombre"));
                menPojo.setNombre(sensorApi.getString("nombre"));
                Log.d("RestApi","respuesta "+sensorApi.getString("salon"));
                menPojo.setSalon(sensorApi.getString("salon"));
                Log.d("RestApi","respuesta "+sensorApi.getString("horario"));
                menPojo.setHorario(sensorApi.getString("horario"));
                Log.d("RestApi","respuesta "+sensorApi.getString("codigo"));
                menPojo.setCodigo(sensorApi.getString("codigo"));
                Log.d("RestApi","respuesta "+sensorApi.getString("img"));
               // menPojo.setRecomendaciones(sensorApi.getString("img"));
                Log.d("RestApi","respuesta "+sensorApi.getString("dia"));
                menPojo.setFecha(sensorApi.getString("dia"));
                lMenu.add(menPojo);
            }
        }
        catch (Exception ex){

        }

        return lMenu;
    }

    private static class RequestApi extends AsyncTask<Request, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(Request... params) {
            try {

                OkHttpClient client = new OkHttpClient();
                Response response = client.newCall(params[0]).execute();

                String string = response.body().string();
                JSONArray jsonObjects = new JSONArray(string);

                return jsonObjects;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
    }
}
