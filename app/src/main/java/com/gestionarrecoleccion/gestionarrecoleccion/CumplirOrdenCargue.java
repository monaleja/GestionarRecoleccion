package com.gestionarrecoleccion.gestionarrecoleccion;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gestionarrecoleccion.gestionarrecoleccion.adapters.AdapterListaRemesa;
import com.gestionarrecoleccion.gestionarrecoleccion.config.ConfigurarCliente;
import com.gestionarrecoleccion.gestionarrecoleccion.entidades.RegionalEntidad;
import com.gestionarrecoleccion.gestionarrecoleccion.entidades.RemesaEntidad;
import com.gestionarrecoleccion.gestionarrecoleccion.utils.RespuestaRest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Alejandra on 03/12/2016.
 */
public class CumplirOrdenCargue extends AppCompatActivity {
    TextView tvUsuarioLogin;
    TextView tvUsuarioCentrocosto;
    TextView tvOrdenCargueCodigo;
    EditText etRemesa;
    EditText etPeso;
    EditText etCantidad;
    Spinner spRegionalDestino;
    String plarecCodigo;
    ImageView ivCerrarSesion;
    SharedPreferences sharedPref;
    ListView lvRemesas;
    ArrayList<RegionalEntidad> objArrayRegional = new ArrayList<RegionalEntidad>();
    ArrayAdapter<RegionalEntidad> adapterRegional;
    ArrayList<RemesaEntidad> objArrayRemesa;
    AdapterListaRemesa adapterlistaremesa;
    final CharSequence[] items = {"Eliminar detalle"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cumplir_ordencargue);
        initComponents();

        SharedPreferences sharedPref = getSharedPreferences("DatosSesionRedetransMovil", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("valorCodigoDeBarras", "");
        editor.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPref = getSharedPreferences("DatosSesionRedetransMovil",Context.MODE_PRIVATE);
        etRemesa.setText(sharedPref.getString("valorCodigoDeBarras", ""));
    }

    public void initComponents()
    {
        Intent intent = getIntent();
        sharedPref = getSharedPreferences("DatosSesionRedetransMovil",Context.MODE_PRIVATE);
        tvUsuarioLogin = (TextView) findViewById(R.id.tvUsuarioLogin);
        tvUsuarioLogin.setText(sharedPref.getString("usuarioLogin", ""));
        tvUsuarioCentrocosto = (TextView) findViewById(R.id.tvUsuarioCentrocosto);
        tvUsuarioCentrocosto.setText(sharedPref.getString("cencosNombre", ""));
        ivCerrarSesion = (ImageView) findViewById(R.id.imageView);
        tvOrdenCargueCodigo = (TextView) findViewById(R.id.tvOrdenCargueCodigo);
        tvOrdenCargueCodigo.setText(intent.getStringExtra("ordenCargueCodigo"));
        etRemesa = (EditText) findViewById(R.id.etRemesa);
        etPeso = (EditText) findViewById(R.id.etPeso);
        etCantidad = (EditText) findViewById(R.id.etCantidad);
        spRegionalDestino = (Spinner) findViewById(R.id.spRegionalDestino);
        poblarRegional();
        plarecCodigo = intent.getStringExtra("planRecogidaCodigo");
        objArrayRemesa = new ArrayList<RemesaEntidad>();
    }

    public void poblarRegional()
    {
        objArrayRegional.add(new RegionalEntidad("0","SELECCIONE UNO"));
        objArrayRegional.add(new RegionalEntidad("1","CENTRO"));
        objArrayRegional.add(new RegionalEntidad("2","ANTIOQUIA"));
        objArrayRegional.add(new RegionalEntidad("3","OCCIDENTE"));
        objArrayRegional.add(new RegionalEntidad("4","EJE CAFETERO"));
        objArrayRegional.add(new RegionalEntidad("5","SANTANDER SUR"));
        objArrayRegional.add(new RegionalEntidad("6","COSTA NORTE"));
        objArrayRegional.add(new RegionalEntidad("7","TOLIMA GRANDE"));
        objArrayRegional.add(new RegionalEntidad("8","COSTA BAJA PLANETA"));
        objArrayRegional.add(new RegionalEntidad("9","SANTANDER NORTE"));
        objArrayRegional.add(new RegionalEntidad("10","COSTA CARIBE"));
        objArrayRegional.add(new RegionalEntidad("13","COSTA MEDIA SINCELEJO"));
        objArrayRegional.add(new RegionalEntidad("25","NACIONAL PRINCIPAL"));

        adapterRegional = new ArrayAdapter<RegionalEntidad>(this, android.R.layout.simple_spinner_dropdown_item, objArrayRegional);
        spRegionalDestino.setAdapter(adapterRegional);
    }

    public void escanearCodigoDeBarras(View view)
    {
        Intent intent = new Intent(CumplirOrdenCargue.this, EscanearCodigoDeBarras.class);
        startActivity(intent);
    }

    public void adicionarRemesa(View view)
    {
        RegionalEntidad regional = (RegionalEntidad)spRegionalDestino.getSelectedItem();

        if(validacionCamposDetalle(view)) {
            if(validacionRemesaDetalle(view)){
                objArrayRemesa.add(new RemesaEntidad(
                        etRemesa.getText().toString(),
                        etPeso.getText().toString(),
                        etCantidad.getText().toString(),
                        tvOrdenCargueCodigo.getText().toString(),
                        regional.getRegionalCodigo(),
                        regional.getRegionalNombre(),
                        plarecCodigo
                ));

                adapterlistaremesa = new AdapterListaRemesa(CumplirOrdenCargue.this, objArrayRemesa);
                lvRemesas = (ListView) findViewById(R.id.lvRemesas);
                lvRemesas.setAdapter(adapterlistaremesa);
                setEventoTapDetalle();

                etRemesa.setText("");
                etPeso.setText("");
                etCantidad.setText("");
                spRegionalDestino.setSelection(0);
            }
        }
    }

    public boolean validacionCamposDetalle(View view){
        RegionalEntidad regional = (RegionalEntidad)spRegionalDestino.getSelectedItem();

        if(etRemesa.getText().toString().equals("") || etPeso.getText().toString().equals("")
                || etCantidad.getText().toString().equals("") || regional.getRegionalCodigo().equals("0")){
            new AlertDialog.Builder(view.getContext())
                    .setTitle("Validación")
                    .setMessage("Faltan campos por diligenciar.")
                    .setPositiveButton(android.R.string.yes, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return false;
        }else{
            return true;
        }
    }

    public boolean validacionRemesaDetalle(View view){
        Integer remesaContador = 0;

        for (int i = 0; i < objArrayRemesa.size(); i++) {
            if(objArrayRemesa.get(i).getRemesaCodigo().equals(etRemesa.getText().toString())){
                remesaContador++;
            }
        }

        if(remesaContador > 0){
            new AlertDialog.Builder(view.getContext())
                    .setTitle("Validación")
                    .setMessage("La remesa ya se adicionó al detalle.")
                    .setPositiveButton(android.R.string.yes, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return false;
        }else{
            return true;
        }
    }

    public void setEventoTapDetalle() {
        lvRemesas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CumplirOrdenCargue.this);
                builder.setTitle("Menú");

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(items[which].toString().equals("Eliminar detalle")){
                            objArrayRemesa.remove(parent.getItemAtPosition(position));
                            adapterlistaremesa.notifyDataSetChanged();
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public void guardarCumplido(View view)
    {
        if(objArrayRemesa.size() == 0){
            new AlertDialog.Builder(view.getContext())
                    .setTitle("Validación")
                    .setMessage("Debe diligenciar al menos un detalle para cumplir la orden de cargue.")
                    .setPositiveButton(android.R.string.yes, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else{
            Gson gson = new GsonBuilder().create();
            JsonArray jsonArray = gson.toJsonTree(objArrayRemesa).getAsJsonArray();

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Cumpliendo orden cargue...");
            progressDialog.show();

            AsyncHttpClient cliente = new ConfigurarCliente(new AsyncHttpClient(), getApplicationContext()).getCliente();

            RequestParams requestParams = new RequestParams();
            requestParams.add("accion","cumplirOrdenCargue");
            requestParams.add("usuarioCodigo", sharedPref.getString("usuarioCodigo", ""));
            requestParams.add("cencosCodigo", sharedPref.getString("cencosCodigo", ""));
            requestParams.add("cadena", jsonArray.toString());

            cliente.post("http://181.48.247.202/redetransmovil/apps/gestionarRecoleccion/gestionarRecoleccion.php", requestParams, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    RespuestaRest respuestaRest = new RespuestaRest(response);

                    if (respuestaRest.satisfactorio) {
                        Toast.makeText(getApplicationContext(), respuestaRest.mensaje, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(CumplirOrdenCargue.this, ListaOrdenCargue.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), respuestaRest.mensaje, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFinish() {
                    progressDialog.dismiss();
                }
            });
        }
    }

    public void cancelarCumplido(View view)
    {
        Intent intent = new Intent(CumplirOrdenCargue.this, ListaOrdenCargue.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void cerrarSesion(View view)
    {
        destruirSesion();
    }

    public void destruirSesion()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(CumplirOrdenCargue.this);
        builder.setTitle("Cerrar sesión");
        builder.setMessage("¿Desea cerrar sesión?");

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SharedPreferences sharedPref = getSharedPreferences("DatosSesionRedetransMovil", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.clear().commit();

                Intent intent = new Intent(CumplirOrdenCargue.this, Principal.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}