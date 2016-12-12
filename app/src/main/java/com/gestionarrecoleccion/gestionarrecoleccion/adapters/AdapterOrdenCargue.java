package com.gestionarrecoleccion.gestionarrecoleccion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gestionarrecoleccion.gestionarrecoleccion.R;
import com.gestionarrecoleccion.gestionarrecoleccion.entidades.OrdenCargueEntidad;

import java.util.ArrayList;

/**
 * Created by Alejandra on 28/11/16.
 */

public class AdapterOrdenCargue extends BaseAdapter {

    private Context context;
    private ArrayList<OrdenCargueEntidad> ordenCargueEntidad;

    public AdapterOrdenCargue(Context context, ArrayList<OrdenCargueEntidad> ordenCargueEntidad) {
        this.context = context;
        this.ordenCargueEntidad = ordenCargueEntidad;
    }

    @Override
    public int getCount() {
        return ordenCargueEntidad.size();
    }

    @Override
    public Object getItem(int i) {
        return ordenCargueEntidad.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.activity_list_row_ordencargue, viewGroup, false);
        }

        OrdenCargueEntidad ordenCargueActual= (OrdenCargueEntidad) getItem(i);

        TextView tvPlanRecogidaCodigo = (TextView)view.findViewById(R.id.tvPlarecCodigo);
        TextView tvOrdcarCodigo = (TextView) view.findViewById(R.id.tvOrdcarCodigo);
        TextView tvRemitenteNombre = (TextView) view.findViewById(R.id.tvRemitenteNombre);
        TextView tvRemitenteDireccion = (TextView) view.findViewById(R.id.tvRemitenteDireccion);
        TextView tvRemitenteTelefono = (TextView) view.findViewById(R.id.tvRemitenteTelefono);
        TextView tvOrdcarHora = (TextView) view.findViewById(R.id.tvOrdcarHora);

        tvPlanRecogidaCodigo.setText(ordenCargueActual.getPlanRecogidaCodigo());
        tvOrdcarCodigo.setText(ordenCargueActual.getOrdenCargueCodigo());
        tvRemitenteNombre.setText(ordenCargueActual.getRemitenteNombre());
        tvRemitenteDireccion.setText(ordenCargueActual.getRemitenteDireccion());
        tvRemitenteTelefono.setText(ordenCargueActual.getRemitenteTelefono());
        tvOrdcarHora.setText(ordenCargueActual.getOrdenCargueHora());

        return view;
    }
}