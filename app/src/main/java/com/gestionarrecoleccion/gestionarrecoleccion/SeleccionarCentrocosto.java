package com.gestionarrecoleccion.gestionarrecoleccion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gestionarrecoleccion.gestionarrecoleccion.adapters.AdapterEmpresaUsuario;
import com.gestionarrecoleccion.gestionarrecoleccion.entidades.EmpresaUsuarioEntidad;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Alejandra on 27/11/2016.
 */
public class SeleccionarCentrocosto extends ActionBarActivity {

    JSONArray empresasJson = new JSONArray();
    ArrayList<EmpresaUsuarioEntidad> empresas = new ArrayList<EmpresaUsuarioEntidad>();
    ListView lvEmpresas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_centrocosto);
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
                EmpresaUsuarioEntidad empresaUsuarioEntidad = new EmpresaUsuarioEntidad(
                        empresa.getString("usuario_codigo"),
                        empresa.getString("usuario_login"),
                        empresa.getString("empresa_codigo"),
                        empresa.getString("empresa_nombre"),
                        empresa.getString("cencos_codigo"),
                        empresa.getString("cencos_nombre"));

                guardarDatosSesion(empresaUsuarioEntidad);
                goSeleccionarEmpresaToMenuAplicaciones(empresaUsuarioEntidad);
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
                empresas.add(new EmpresaUsuarioEntidad(
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
                final EmpresaUsuarioEntidad empresa = (EmpresaUsuarioEntidad) parent.getItemAtPosition(position);
                guardarDatosSesion(empresa);
                goSeleccionarEmpresaToMenuAplicaciones(empresa);
            }
        });
    }

    public void guardarDatosSesion(EmpresaUsuarioEntidad empresaUsuarioEntidad)
    {
        SharedPreferences sharedPref = getSharedPreferences("DatosSesionRedetransMovil", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("usuarioCodigo", empresaUsuarioEntidad.getUsuarioCodigo());
        editor.putString("usuarioLogin", empresaUsuarioEntidad.getUsuarioLogin());
        editor.putString("empresaCodigo", empresaUsuarioEntidad.getEmpresaCodigo());
        editor.putString("empresaNombre", empresaUsuarioEntidad.getEmpresaNombre());
        editor.putString("cencosCodigo", empresaUsuarioEntidad.getCencosCodigo());
        editor.putString("cencosNombre", empresaUsuarioEntidad.getCencosNombre());
        editor.commit();
    }

    public void goSeleccionarEmpresaToMenuAplicaciones (EmpresaUsuarioEntidad empresa){

        Intent intent = new Intent(SeleccionarCentrocosto.this, ListaOrdenCargue.class);
        intent.putExtra("usuarioLogin", empresa.getUsuarioLogin());
        intent.putExtra("usuarioCencos", empresa.getCencosNombre());

        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);

        startActivity(intent);
    }
}