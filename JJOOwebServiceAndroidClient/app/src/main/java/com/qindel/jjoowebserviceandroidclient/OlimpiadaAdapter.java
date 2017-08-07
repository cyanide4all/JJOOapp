package com.qindel.jjoowebserviceandroidclient;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Qindel on 04/08/2017.
 */

public class OlimpiadaAdapter extends BaseAdapter {

    private ArrayList<HashMap<String,String>> data;

    public OlimpiadaAdapter(ArrayList<HashMap<String,String>> data){
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) viewGroup.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.main_list_item, viewGroup, false);
        }

        ((TextView) view.findViewById(R.id.id_ciudad)).setText(data.get(i).get("ID_CIUDAD"));
        ((TextView) view.findViewById(R.id.id_pais)).setText(data.get(i).get("ID_PAIS"));
        ((TextView) view.findViewById(R.id.nombre_pais)).setText(data.get(i).get("NOMBRE_PAIS"));
        ((TextView) view.findViewById(R.id.nombre_ciudad)).setText(data.get(i).get("NOMBRE_CIUDAD"));
        ((TextView) view.findViewById(R.id.valor)).setText(data.get(i).get("VALOR"));
        ((TextView) view.findViewById(R.id.veces_sede)).setText(data.get(i).get("NUMERO_VECES_SEDE"));
        ((TextView) view.findViewById(R.id.tipo_jjoo)).setText(data.get(i).get("DESCRIPCION_TIPO"));

        return view;
    }
}
