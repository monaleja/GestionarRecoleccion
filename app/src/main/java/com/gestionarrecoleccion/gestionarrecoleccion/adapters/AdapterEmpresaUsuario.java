package com.gestionarrecoleccion.gestionarrecoleccion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gestionarrecoleccion.gestionarrecoleccion.R;
import com.gestionarrecoleccion.gestionarrecoleccion.modelos.EmpresaUsuario;

import java.util.ArrayList;

/**
 * Created by Alejandra on 27/11/2016
 */
public class AdapterEmpresaUsuario extends BaseAdapter {

    private Context context;
    private ArrayList<EmpresaUsuario> empresas;

    public AdapterEmpresaUsuario(Context context, ArrayList<EmpresaUsuario> empresas) {
        this.context = context;
        this.empresas = empresas;
    }

    @Override
    public int getCount() {
        return empresas.size();
    }

    @Override
    public Object getItem(int i) {
        return empresas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_empresas, viewGroup, false);
        }

        EmpresaUsuario empresaActual = (EmpresaUsuario) getItem(i);

        TextView tvLvEmpresa = (TextView) view.findViewById(R.id.tvLvEmpresa);
        TextView tvLvCencos = (TextView) view.findViewById(R.id.tvLvCencos);

        //tvLvEmpresa.setText(empresaActual.getEmpresaNombre());
        tvLvEmpresa.setText(null);
        tvLvCencos.setText(empresaActual.getCencosNombre());

        return view;
    }
}