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
import android.widget.ListView;
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
    boolean pruebas;
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
                //setEventoTapSostenidoEmpresa();
            }else{
                JSONObject empresa = empresasJson.getJSONObject(0);
                EmpresaUsuario empresaUsuario = new EmpresaUsuario(
                        empresa.getString("usuario_codigo"),
                        empresa.getString("usuario_login"),
                        empresa.getString("empresa_codigo"),
                        empresa.getString("empresa_nombre"),
                        empresa.getString("cencos_codigo"),
                        empresa.getString("cencos_nombre"));

                guardarDatosSesion(empresaUsuario, false);
                goSeleccionarEmpresaToMenuAplicaciones(empresaUsuario);
            }

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Algo salio mal, por favor contactar con el área de soporte.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seleccionar_empresa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                pruebas = false;
                final EmpresaUsuario empresa = (EmpresaUsuario) parent.getItemAtPosition(position);
                guardarDatosSesion(empresa, false);
                goSeleccionarEmpresaToMenuAplicaciones(empresa);
            }
        });
    }

    public void guardarDatosSesion(EmpresaUsuario empresaUsuario,boolean pruebas)
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
        Toast.makeText(getApplicationContext(), "Tap centro costo: "+empresa.getCencosNombre(), Toast.LENGTH_LONG).show();
        abrirDialogo(this);

        /*Intent intent = new Intent(SeleccionarEmpresa.this, MenuAplicaciones.class);

        intent.putExtra("usuarioLogin", empresa.getUsuarioLogin());
        intent.putExtra("usuarioEmpresa", empresa.getEmpresaNombre());
        intent.putExtra("usuarioCencos", empresa.getCencosNombre());

        startActivity(intent);*/
    }

    public void abrirDialogo(Activity activity){
        DialogAgregarNovedad = new Dialog(activity);
        DialogAgregarNovedad.setContentView(R.layout.dialog_agregar_novedad);
        DialogAgregarNovedad.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        DialogAgregarNovedad.show();
    }

    public void setEventoTapSostenidoEmpresa()
    {
        lvEmpresas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                EmpresaUsuario empresa = (EmpresaUsuario) parent.getItemAtPosition(position);

                /*if(empresa.getUsuarioLogin().equals("AOSPINA") || empresa.getUsuarioLogin().equals("JDCHICA") || empresa.getUsuarioLogin().equals("JCGOMEZ")){
                    empresa.setUsuarioIp("181.48.247.202");
                    Toast.makeText(getApplicationContext(), "Modo de pruebas activado.", Toast.LENGTH_LONG).show();
                }*/

                guardarDatosSesion(empresa,true);

                /*Intent intent = new Intent(SeleccionarEmpresa.this, MenuAplicaciones.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("usuarioLogin", empresa.getUsuarioLogin());
                intent.putExtra("usuarioEmpresa", empresa.getEmpresaNombre());
                intent.putExtra("usuarioCencos", empresa.getCencosNombre());

                startActivity(intent);
                */

                return true;
            }
        });
    }
}