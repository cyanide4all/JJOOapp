package com.qindel.jjoowebserviceandroidclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Qindel on 04/08/2017.
 */

public class SedesAdapter extends BaseAdapter {

    private ArrayList<HashMap<String,String>> data;

    public SedesAdapter(ArrayList<HashMap<String,String>> data){
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
            view = inflater.inflate(R.layout.sedes_list_item, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.nombre_sede)).setText(data.get(i).get("NOMBRE_CIUDAD"));
        ((TextView) view.findViewById(R.id.anyo)).setText(data.get(i).get("ANYO"));
        ((TextView) view.findViewById(R.id.tipo_jjoo2)).setText(data.get(i).get("DESCRIPCION_TIPO"));

        return view;
    }
}
