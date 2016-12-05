package com.gestionarrecoleccion.gestionarrecoleccion;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.gestionarrecoleccion.gestionarrecoleccion.adapters.AdapterEmpresaUsuario;
import com.gestionarrecoleccion.gestionarrecoleccion.modelos.EmpresaUsuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Alejandra on 27/11/2016. :P :P :P
 */
public class SeleccionarEmpresa extends ActionBarActivity {

    JSONArray empresasJson = new JSONArray();
    ArrayList<EmpresaUsuario> empresas = new ArrayList<EmpresaUsuario>();
    ListView lvEmpresas;
    Dialog DialogAgregarNovedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_empresa);
        Intent intent = getIntent();

        try {
            empresasJson = new JSONArray(intent.getStringExtra("empresasJson"));

            if(empresasJson.length() > 1) {
                poblarListaEmpresas();
                AdapterEmpresaUsuario adapterEmpresaUsuario = new AdapterEmpresaUsuario(this, empresas);
                lvEmpresas = (ListView) findViewById(R.id.lvEmpresas);
                lvEmpresas.setAdapter(adapterEmpresaUsuario);
                setEventoTapEmpresa();
            }else{
                JSONObject empresa = empresasJson.getJSONObject(0);
                EmpresaUsuario empresaUsuario = new EmpresaUsuario(
                        empresa.getString("usuario_codigo"),
                        empresa.getString("usuario_login"),
                        empresa.getString("empresa_codigo"),
                        empresa.getString("empresa_nombre"),
                        empresa.getString("cencos_codigo"),
                        empresa.getString("cencos_nombre"));

                guardarDatosSesion(empresaUsuario);
                goSeleccionarEmpresaToMenuAplicaciones(empresaUsuario);
            }

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Algo salio mal, por favor contactar con el área de soporte.", Toast.LENGTH_SHORT).show();
        }
    }

    public void poblarListaEmpresas()
    {
        for (int i = 0; i < empresasJson.length(); i++) {
            try {
                JSONObject empresa = empresasJson.getJSONObject(i);
                empresas.add(new EmpresaUsuario(
                        empresa.getString("usuario_codigo"),
                        empresa.getString("usuario_login"),
                        empresa.getString("empresa_codigo"),
                        empresa.getString("empresa_nombre"),
                        empresa.getString("cencos_codigo"),
                        empresa.getString("cencos_nombre")));
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Algo salio mal, por favor contactar con el área de soporte.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setEventoTapEmpresa() {
        lvEmpresas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final EmpresaUsuario empresa = (EmpresaUsuario) parent.getItemAtPosition(position);
                guardarDatosSesion(empresa);
                goSeleccionarEmpresaToMenuAplicaciones(empresa);
            }
        });
    }

    public void guardarDatosSesion(EmpresaUsuario empresaUsuario)
    {
        SharedPreferences sharedPref = getSharedPreferences("DatosSesionRedetransMovil", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("usuarioCodigo", empresaUsuario.getUsuarioCodigo());
        editor.putString("usuarioLogin", empresaUsuario.getUsuarioLogin());
        editor.putString("empresaCodigo", empresaUsuario.getEmpresaCodigo());
        editor.putString("empresaNombre", empresaUsuario.getEmpresaNombre());
        editor.putString("cencosCodigo", empresaUsuario.getCencosCodigo());
        editor.putString("cencosNombre", empresaUsuario.getCencosNombre());
        editor.commit();
    }

    public void goSeleccionarEmpresaToMenuAplicaciones (EmpresaUsuario empresa){

        Intent intent = new Intent(SeleccionarEmpresa.this, ListaOrdenCargue.class);
        intent.putExtra("usuarioLogin", empresa.getUsuarioLogin());
        intent.putExtra("usuarioCencos", empresa.getCencosNombre());

        //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        abrirDialogo(SeleccionarEmpresa.this);
        //startActivity(intent);
    }
<<<<<<< HEAD
=======

    public void abrirDialogo(Activity activity){
        ArrayList<String> TipoNovedad = new ArrayList<String>();

        TipoNovedad.add(0,"0-Seleccione Uno");
        TipoNovedad.add(1,"34-Recoleccion no autorizada");
        TipoNovedad.add(2,"52-Direccion de recoleccion errada");
        TipoNovedad.add(3,"53-Mercancia no esta lista");
        TipoNovedad.add(4,"54-El cliente no tiene conocimiento de la recoleccion");
        TipoNovedad.add(5,"55-Cupo");

        DialogAgregarNovedad = new Dialog(activity);
        DialogAgregarNovedad.setContentView(R.layout.dialog_agregar_novedad);
        DialogAgregarNovedad.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        DialogAgregarNovedad.show();

        Spinner spTipoNovedad = (Spinner) DialogAgregarNovedad.findViewById(R.id.spTipoNovedad);
        spTipoNovedad.setAdapter(new ArrayAdapter<String>(SeleccionarEmpresa.this, android.R.layout.simple_spinner_dropdown_item, TipoNovedad));
        //spTipoNovedad.setSelection(0);

    }
>>>>>>> eddc17c5ab3fb5fd71512de2c83b15ef2bb1a3fc
}