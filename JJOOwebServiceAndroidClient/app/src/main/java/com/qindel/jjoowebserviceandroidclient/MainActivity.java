package com.qindel.jjoowebserviceandroidclient;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    private ArrayList<HashMap<String,String>> data;
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        final Intent manageSedes = new Intent(this,SedesManagement.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(manageSedes);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        lista = (ListView) findViewById(R.id.ListViewHome);
        new AsyncTask<Void, Void, ArrayList<HashMap<String, String>>>() {
            @Override
            protected ArrayList<HashMap<String, String>> doInBackground(Void... voids) {
                try {
                    //Sacar esto a una clase auxiliar pronto
                    final String url = "http://172.26.80.76:8080/olimpiadas";
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    ArrayList<HashMap<String,String>> data = restTemplate.getForObject(url, new ArrayList<HashMap<String,String>>().getClass());
                    return data;
                } catch (Exception e) {
                    Log.e("MainActivity", e.getMessage(), e);
                }

                return null;
            }
            @Override
            protected void onPostExecute(ArrayList<HashMap<String,String>> restResponse) {
                data = restResponse;
                lista.setAdapter(new OlimpiadaAdapter(data));
            }
        }.execute();
    }
}
