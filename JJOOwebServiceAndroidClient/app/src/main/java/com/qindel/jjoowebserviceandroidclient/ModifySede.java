package com.qindel.jjoowebserviceandroidclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

public class ModifySede extends AppCompatActivity {

    private HashMap<String,String> sede;
    private String[] ciudades;
    private Spinner comboCiudades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_sede);
        comboCiudades = (Spinner) findViewById(R.id.sede_modify);
        sede = (HashMap<String,String>)getIntent().getExtras().getSerializable("sede");


        ((TextView) findViewById(R.id.anyo_modify)).setText(sede.get("ANYO"));


        ((TextView) findViewById(R.id.tipo_jjoo_modify)).setText(sede.get("DESCRIPCION_TIPO"));

        Button botonModificar = (Button) findViewById(R.id.boton_modificar_sede);
        botonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id_sede = comboCiudades.getSelectedItemPosition()+1;
                new AsyncTask<Void, Void, String[]>() {
                    @Override
                    protected String[] doInBackground(Void... voids) {
                        try {
                            //Sacar esto a una clase auxiliar pronto
                            RestTemplate restTemplate = new RestTemplate();
                            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                            restTemplate.put("http://172.26.80.76:8080/sedes/"+sede.get("ANYO")+"/"+sede.get("ID_TIPO_JJOO"), id_sede);
                        } catch (Exception e) {
                            Log.e("modify_sede", e.getMessage(), e);
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
                        return text;
                    }
                });
                comboCiudades.setSelection(Arrays.asList(ciudades).indexOf(sede.get("NOMBRE_CIUDAD")));
            }
        }.execute();

    }


}
