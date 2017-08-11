package com.qindel.jjoowebserviceandroidclient;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class SedesManagement extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> data;
    private ListView lista;
    private Context actividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sedes_management);

        actividad = this;
        Button button = (Button) findViewById(R.id.buttonSedes);
        final Intent addSede = new Intent(this,AddSede.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(addSede);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        lista = (ListView) findViewById(R.id.ListViewSedes);

        renderLista();

    }

    /**
     * Workaround method por no haber usado restTemplates donde era conveniente en el servicio web
     */
    private void renderLista() {
        new AsyncTask<Void, Void, ArrayList<HashMap<String, String>>>() {
            /**
             * Hacemos la peticion a /sedes
             * @param voids
             * @return Lista de mapas con las sedes
             */
            @Override
            protected ArrayList<HashMap<String, String>> doInBackground(Void... voids) {
                try {
                    //Sacar esto a una clase auxiliar pronto
                    final String url = "http://172.26.80.76:8080/sedes";
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    ArrayList<HashMap<String,String>> data = restTemplate.getForObject(url, new ArrayList<HashMap<String,String>>().getClass());
                    return data;
                } catch (Exception e) {
                    Log.e("SedesManagement", e.getMessage(), e);
                }

                return null;
            }

            /**
             * Se ejecuta tras la peticion, establece los datos en "data" y da la orden de
             * renderizar a través del adapter
             * @param restResponse dado por el padre, son los datos obtenidos de REST
             */
            @Override
            protected void onPostExecute(final ArrayList<HashMap<String,String>> restResponse) {
                data = restResponse;
                lista.setAdapter(new SedesAdapter(data));
                lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        // Use the Builder class for convenient dialog construction
                        final int currentItem = i;
                        AlertDialog.Builder builder = new AlertDialog.Builder(actividad);
                        builder.setMessage("¿Borrar esta sede definitivamente?")
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        new AsyncTask<Void, Void, String[]>() {
                                            @Override
                                            protected String[] doInBackground(Void... voids) {
                                                try {
                                                    URL url = new URL("http://172.26.80.76:8080/sedes/"+data.get(currentItem).get("ANYO")+"/"+data.get(currentItem).get("ID_TIPO_JJOO"));
                                                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                                    conn.setRequestMethod("DELETE");
                                                    conn.getResponseCode();
                                                    conn.disconnect();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                return null;
                                            }
                                            @Override
                                            protected void onPostExecute(String[] restResponse) {
                                                renderLista();
                                            }
                                        }.execute();
                                    }
                                })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        // Create the AlertDialog object and return it
                        builder.create().show();
                        return true;
                    }
                });
                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intento = new Intent(actividad, ModifySede.class);
                        intento.putExtra("sede", data.get(i));
                        startActivity(intento);
                    }
                });
            }
        }.execute();
    }
}
