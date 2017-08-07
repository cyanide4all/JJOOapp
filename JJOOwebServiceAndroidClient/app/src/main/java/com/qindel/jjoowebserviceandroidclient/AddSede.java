package com.qindel.jjoowebserviceandroidclient;

import android.app.AlertDialog;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.fasterxml.jackson.databind.deser.Deserializers;

import org.springframework.http.ContentCodingType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class AddSede extends AppCompatActivity {

    private String[] ciudades;
    private String[] tipos_JJOO;
    private Spinner comboCiudades;
    private Spinner comboTiposJJOO;
    private int[] body;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sede);
        comboCiudades = (Spinner) findViewById(R.id.sede_select);
        comboTiposJJOO = (Spinner) findViewById(R.id.tipo_select);
        final EditText anyo = (EditText) findViewById(R.id.EditAnyo);
        Button botonCrear = (Button) findViewById(R.id.boton_crear_sede);
        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                body = new int[3];
                body[0] = Integer.parseInt(anyo.getText().toString());
                body[1] = comboTiposJJOO.getSelectedItemPosition()+1;
                body[2] = comboCiudades.getSelectedItemPosition()+1;
                new AsyncTask<Void, Void, String[]>() {
                    @Override
                    protected String[] doInBackground(Void... voids) {
                        try {
                            RestTemplate restTemplate = new RestTemplate();
                            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                            restTemplate.postForLocation("http://172.26.80.76:8080/sedes", body);

                        } catch (Exception e) {
                            Log.e("AddSede", e.getMessage(), e);
                        }

                        return null;
                    }
                    @Override
                    protected void onPostExecute(String[] restResponse) {
                        finish();
                    }
                }.execute();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Llenar el select de ciudad
        new AsyncTask<Void, Void, String[]>() {
            @Override
            protected String[] doInBackground(Void... voids) {
                try {
                    //Sacar esto a una clase auxiliar pronto
                    final String url = "http://172.26.80.76:8080/ciudades/nombres";
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    String[] data = restTemplate.getForObject(url, String[].class);
                    return data;
                } catch (Exception e) {
                    Log.e("MainActivity", e.getMessage(), e);
                }

                return null;
            }
            @Override
            protected void onPostExecute(String[] restResponse) {
                ciudades = restResponse;
                comboCiudades.setAdapter(new BaseAdapter() {

                    @Override
                    public int getCount() {
                        return ciudades.length;
                    }

                    @Override
                    public Object getItem(int i) {
                        return ciudades[i];
                    }

                    @Override
                    public long getItemId(int i) {
                        return i;
                    }

                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        TextView text = new TextView(getApplicationContext());
                        text.setText(ciudades[i]);
                        return text;                    }
                });
            }
        }.execute();

        //Llenar el select de tipo de juego
        new AsyncTask<Void, Void, String[]>() {
            @Override
            protected String[] doInBackground(Void... voids) {
                try {
                    //Sacar esto a una clase auxiliar pronto
                    final String url = "http://172.26.80.76:8080/tipoJJOO/descripciones";
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    String[] data = restTemplate.getForObject(url, String[].class);
                    return data;
                } catch (Exception e) {
                    Log.e("MainActivity", e.getMessage(), e);
                }

                return null;
            }
            @Override
            protected void onPostExecute(String[] restResponse) {
                tipos_JJOO = restResponse;
                comboTiposJJOO.setAdapter(new BaseAdapter() {

                    @Override
                    public int getCount() {
                        return tipos_JJOO.length;
                    }

                    @Override
                    public Object getItem(int i) {
                        return tipos_JJOO[i];
                    }

                    @Override
                    public long getItemId(int i) {
                        return i;
                    }

                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        TextView text = new TextView(getApplicationContext());
                        text.setText(tipos_JJOO[i]);
                        return text;
                    }
                });
            }
        }.execute();

    }
}
