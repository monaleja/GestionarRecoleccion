package com.gestionarrecoleccion.gestionarrecoleccion;

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
 * Created by Alejandra on 27/11/2016.
 */
public class SeleccionarEmpresa extends ActionBarActivity {

    JSONArray empresasJson = new JSONArray();
    ArrayList<EmpresaUsuario> empresas = new ArrayList<EmpresaUsuario>();
    ListView lvEmpresas;
    boolean pruebas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_empresa);
        Intent intent = getIntent();

        try {
            empresasJson = new JSONArray(intent.getStringExtra("empresasJson"));
            poblarListaEmpresas();

            AdapterEmpresaUsuario adapterEmpresaUsuario= new AdapterEmpresaUsuario(this, empresas);
            lvEmpresas = (ListView) findViewById(R.id.lvEmpresas);
            lvEmpresas.setAdapter(adapterEmpresaUsuario);
            setEventoTapEmpresa();
            setEventoTapSostenidoEmpresa();

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
                        empresa.getString("cencos_nombre"),
                        empresa.getString("usuario_implementador")));
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Algo salio mal, por favor contactar con el área de soporte.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setEventoTapEmpresa()
    {
        lvEmpresas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pruebas = false;
                final EmpresaUsuario empresa = (EmpresaUsuario) parent.getItemAtPosition(position);

                if(empresa.getUsuarioImplementador().equalsIgnoreCase("SI")) {
                    new AlertDialog.Builder(SeleccionarEmpresa.this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Servidor de Pruebas")
                            .setMessage("Desea ingresar a las aplicaciones en modo pruebas?")
                            .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    guardarDatosSesion(empresa,true);
                                    goSeleccionarEmpresaToMenuAplicaciones(empresa);
                                }

                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    guardarDatosSesion(empresa, false);
                                    goSeleccionarEmpresaToMenuAplicaciones(empresa);
                                }
                            })
                            .show();
                }else{
                    guardarDatosSesion(empresa, false);
                    goSeleccionarEmpresaToMenuAplicaciones(empresa);
                }
            }
        });
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

    public void guardarDatosSesion(EmpresaUsuario empresaUsuario,boolean pruebas)
    {
        SharedPreferences sharedPref = getSharedPreferences("DatosSesionRedetransMovil", Context.MODE_WORLD_READABLE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("usuarioCodigo", empresaUsuario.getUsuarioCodigo());
        editor.putString("usuarioLogin", empresaUsuario.getUsuarioLogin());
        editor.putString("empresaCodigo", empresaUsuario.getEmpresaCodigo());
        editor.putString("empresaNombre", empresaUsuario.getEmpresaNombre());
        editor.putString("cencosCodigo", empresaUsuario.getCencosCodigo());
        editor.putString("cencosNombre", empresaUsuario.getCencosNombre());

        /*if(pruebas) {
            editor.putString("usuarioIp", empresaUsuario.getUsuarioIpPruebas());
            editor.putString("usuarioCodigoExterno", empresaUsuario.getUsuarioCodigoPruebas());
        }else{
            editor.putString("usuarioIp", empresaUsuario.getUsuarioIp());
            editor.putString("usuarioCodigoExterno", empresaUsuario.getUsuarioCodigoExterno());
        }
        editor.putString("empresaLogin", empresaUsuario.getEmpresaLogin());
        editor.putString("usuarioImplementador",empresaUsuario.getUsuarioImplementador());
        */
        editor.commit();
    }

    public void goSeleccionarEmpresaToMenuAplicaciones (EmpresaUsuario empresa){
        /*Intent intent = new Intent(SeleccionarEmpresa.this, MenuAplicaciones.class);

        intent.putExtra("usuarioLogin", empresa.getUsuarioLogin());
        intent.putExtra("usuarioEmpresa", empresa.getEmpresaNombre());
        intent.putExtra("usuarioCencos", empresa.getCencosNombre());

        startActivity(intent);
        */
    }
}