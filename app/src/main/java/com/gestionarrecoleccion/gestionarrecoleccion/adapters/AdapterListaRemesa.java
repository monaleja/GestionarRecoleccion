package com.gestionarrecoleccion.gestionarrecoleccion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gestionarrecoleccion.gestionarrecoleccion.R;
import com.gestionarrecoleccion.gestionarrecoleccion.modelos.OrdenCargueEntidad;
import com.gestionarrecoleccion.gestionarrecoleccion.modelos.Remesa;

import java.util.ArrayList;

/**
 * Created by Alejandra on 28/11/16.
 */

public class AdapterListaRemesa extends BaseAdapter {

    private Context context;
    private ArrayList<Remesa> remesas;

    public AdapterListaRemesa(Context context, ArrayList<Remesa> remesa) {
        this.context = context;
        this.remesas = remesa;
    }

    @Override
    public int getCount() {
        return remesas.size();
    }

    @Override
    public Object getItem(int i) {
        return remesas.get(i);
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

        Remesa remesa_actual = (Remesa) getItem(i);

        TextView lvremesa_tvRemesa = (TextView)view.findViewById(R.id.lvremesa_tvRemesa);
        TextView lvremesa_tvpeso = (TextView)view.findViewById(R.id.lvremesa_tvpeso);
        TextView lvremesa_tvcantidad = (TextView)view.findViewById(R.id.lvremesa_tvcantidad);
        TextView lvremesa_tvregionaldestino = (TextView)view.findViewById(R.id.lvremesa_tvregionaldestino);


        lvremesa_tvRemesa.setText(remesa_actual.getCodigo());
        lvremesa_tvpeso.setText(""+remesa_actual.getPeso());
        lvremesa_tvcantidad.setText(""+remesa_actual.getCantidad());
        lvremesa_tvregionaldestino.setText(remesa_actual.getRegdesnombre());


        return view;
    }
}
