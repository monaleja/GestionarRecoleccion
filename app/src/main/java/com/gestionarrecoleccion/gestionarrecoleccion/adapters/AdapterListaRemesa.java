package com.gestionarrecoleccion.gestionarrecoleccion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gestionarrecoleccion.gestionarrecoleccion.R;
import com.gestionarrecoleccion.gestionarrecoleccion.entidades.RemesaEntidad;

import java.util.ArrayList;

/**
 * Created by Alejandra on 28/11/16.
 */

public class AdapterListaRemesa extends BaseAdapter {

    private Context context;
    private ArrayList<RemesaEntidad> remesaEntidad;

    public AdapterListaRemesa(Context context, ArrayList<RemesaEntidad> remesaEntidad) {
        this.context = context;
        this.remesaEntidad = remesaEntidad;
    }

    @Override
    public int getCount() {
        return remesaEntidad.size();
    }

    @Override
    public Object getItem(int i) {
        return remesaEntidad.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.activity_list_row_remesa, viewGroup, false);
        }

        RemesaEntidad remesaActual = (RemesaEntidad) getItem(i);

        TextView lvremesa_tvRemesa = (TextView)view.findViewById(R.id.lvremesa_tvRemesa);
        TextView lvremesa_tvpeso = (TextView)view.findViewById(R.id.lvremesa_tvpeso);
        TextView lvremesa_tvcantidad = (TextView)view.findViewById(R.id.lvremesa_tvcantidad);
        TextView lvremesa_tvregionaldestino = (TextView)view.findViewById(R.id.lvremesa_tvregionaldestino);

        lvremesa_tvRemesa.setText(remesaActual.getRemesaCodigo());
        lvremesa_tvpeso.setText(""+ remesaActual.getPeso());
        lvremesa_tvcantidad.setText(""+ remesaActual.getCantidad());
        lvremesa_tvregionaldestino.setText(remesaActual.getRegdesNombre());

        return view;
    }
}