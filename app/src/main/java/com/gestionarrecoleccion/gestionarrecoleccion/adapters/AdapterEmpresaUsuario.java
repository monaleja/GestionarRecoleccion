package com.gestionarrecoleccion.gestionarrecoleccion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gestionarrecoleccion.gestionarrecoleccion.R;
import com.gestionarrecoleccion.gestionarrecoleccion.modelos.EmpresaUsuario;

import java.util.ArrayList;

/**
 * Created by Alejandra on 27/11/2016
 */
public class AdapterEmpresaUsuario extends ArrayAdapter<EmpresaUsuario> {

    public AdapterEmpresaUsuario(Context context, ArrayList<EmpresaUsuario> empresas) {
        super(context, 0, empresas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        EmpresaUsuario empresa = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_empresas, parent, false);
        }
        // Lookup view for data population
        TextView tvLvEmpresa = (TextView) convertView.findViewById(R.id.tvLvEmpresa);
        TextView tvLvCencos = (TextView) convertView.findViewById(R.id.tvLvCencos);
        // Populate the data into the template view using the data object
        tvLvEmpresa.setText(empresa.getEmpresaNombre());
        tvLvCencos.setText(empresa.getCencosNombre());
        // Return the completed view to render on screen
        return convertView;
    }
}