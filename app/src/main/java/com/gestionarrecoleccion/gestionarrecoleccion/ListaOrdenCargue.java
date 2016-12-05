package com.gestionarrecoleccion.gestionarrecoleccion;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gestionarrecoleccion.gestionarrecoleccion.adapters.AdapterOrdenCargue;
import com.gestionarrecoleccion.gestionarrecoleccion.config.ConfigurarCliente;
import com.gestionarrecoleccion.gestionarrecoleccion.modelos.OrdenCargueEntidad;
import com.gestionarrecoleccion.gestionarrecoleccion.modelos.Par;
import com.gestionarrecoleccion.gestionarrecoleccion.modelos.TipoNovedadEntidad;
import com.gestionarrecoleccion.gestionarrecoleccion.utils.RespuestaRest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Alejandra on 28/11/2016.
 */
public class ListaOrdenCargue extends AppCompatActivity {
    ListView lvOrdenCargue;
    TextView tvUsuarioLogin;
    TextView tvUsuarioCentrocosto;
    TextView tvOrdenCargueCodigo;
    EditText etObservacion;
    Button btnGuardarNovedad;
    Button btnCancelarNovedad;
    Spinner spTipoNovedad;
    ImageView ivCerrarSesion;
    JSONArray ordenescargueJson;
    SharedPreferences sharedPref;
    Dialog DialogAgregarNovedad;
    ArrayList<OrdenCargueEntidad> arrayOrdenescargue = new ArrayList<OrdenCargueEntidad>();
    ArrayList<TipoNovedadEntidad> objTipoNovedad = new ArrayList<TipoNovedadEntidad>();
    ArrayAdapter<TipoNovedadEntidad> tipoNovedadAdapter;
    final CharSequence[] items = {"Agregar novedad", "Cumplir orden cargue"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_orden_cargue);
        initComponents();
        listarOrdenes();
        setEventoTapOrdencargue();
    }

    public void initComponents()
    {
        ivCerrarSesion = (ImageView) findViewById(R.id.imageView);
        lvOrdenCargue = (ListView)findViewById(R.id.lvOrdenCargue);
        sharedPref = getSharedPreferences("DatosSesionRedetransMovil",Context.MODE_PRIVATE);
        tvUsuarioLogin = (TextView) findViewById(R.id.tvUsuarioLogin);
        tvUsuarioCentrocosto = (TextView) findViewById(R.id.tvUsuarioCentrocosto);
        tvUsuarioLogin.setText(sharedPref.getString("usuarioLogin", ""));
        tvUsuarioCentrocosto.setText(sharedPref.getString("cencosNombre", ""));
    }

    public void listarOrdenes()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando ordenes de cargue...");
        progressDialog.show();

        AsyncHttpClient cliente = new ConfigurarCliente(new AsyncHttpClient(), getApplicationContext()).getCliente();

        RequestParams requestParams = new RequestParams();
        requestParams.add("accion","ListaOrdenCargue");
        requestParams.add("usuarioCodigo", sharedPref.getString("usuarioCodigo", ""));

        cliente.post(getApplicationContext(), "http://181.48.247.202/redetransmovil/apps/gestionarRecoleccion/gestionarRecoleccion.php", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                RespuestaRest respuestaRest = new RespuestaRest(response);

                if (respuestaRest.satisfactorio) {
                    try {
                        ordenescargueJson = new JSONArray(respuestaRest.respuesta);

                        poblarListaOrdenCargue();

                        AdapterOrdenCargue adapterOrdencargue = new AdapterOrdenCargue(ListaOrdenCargue.this, arrayOrdenescargue);
                        lvOrdenCargue = (ListView) findViewById(R.id.lvOrdenCargue);
                        lvOrdenCargue.setAdapter(adapterOrdencargue);

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "Algo salio mal, por favor contactar con el área de soporte.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), respuestaRest.mensaje, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Algo salio mal, por favor contactar con el área de soporte.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                progressDialog.dismiss();
            }
        });
    }

    public void poblarListaOrdenCargue()
    {

       for (int i = 0; i < ordenescargueJson.length(); i++) {
            try {
                JSONObject ordencargue = ordenescargueJson.getJSONObject(i);
                arrayOrdenescargue.add(new OrdenCargueEntidad(
                                ordencargue.getString("planrecogida"),
                                ordencargue.getString("ordencargue"),
                                ordencargue.getString("remitente_nombre"),
                                ordencargue.getString("remitente_direccion"),
                                ordencargue.getString("remitente_telefono"),
                                ordencargue.getString("hora")));
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Algo salio mal, por favor contactar con el área de soporte.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setEventoTapOrdencargue()
    {
        lvOrdenCargue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListaOrdenCargue.this);
                final OrdenCargueEntidad objOrdencargue = (OrdenCargueEntidad) parent.getItemAtPosition(position);
                builder.setTitle("Menú");

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Log.d("Opción:",""+items[which]);
                        if(items[which].toString().equals("Agregar novedad")){
                            ArrayList<Par> parametros = new ArrayList<Par>();
                            parametros.add(new Par("ordencargue",objOrdencargue.getOrdenCargueCodigo()));
                            abrirDialogo(ListaOrdenCargue.this,parametros);
                        }else
                            if(items[which].toString().equals("Cumplir orden cargue")){
                                Intent intent = new Intent(ListaOrdenCargue.this,CumplirOrdenCargue.class);
                                //intent.putExtra("dataJsonObject", objOrdencargue.getDataJsonObject().toString());
                                startActivity(intent);
                            }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public void abrirDialogo(Activity activity,ArrayList<Par> parametros)
    {
        DialogAgregarNovedad = new Dialog(activity);
        DialogAgregarNovedad.setContentView(R.layout.dialog_agregar_novedad);
        DialogAgregarNovedad.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        DialogAgregarNovedad.show();

        spTipoNovedad = (Spinner) DialogAgregarNovedad.findViewById(R.id.spTipoNovedad);
        poblarTipoNovedad();

        tvOrdenCargueCodigo = (TextView) DialogAgregarNovedad.findViewById(R.id.tvOrdenCargueCodigo);
        etObservacion = (EditText) DialogAgregarNovedad.findViewById(R.id.etObservacion);
        btnGuardarNovedad = (Button) DialogAgregarNovedad.findViewById(R.id.btnGuardarNovedad);
        btnCancelarNovedad = (Button) DialogAgregarNovedad.findViewById(R.id.btnCancelarNovedad);

        for(Par par: parametros){
            if(par.getLlave().equals("ordencargue")){
                tvOrdenCargueCodigo.setText(par.getValor());
                break;
            }
        }

        btnGuardarNovedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarAgregarNovedad();
                DialogAgregarNovedad.dismiss();
            }
        });

        btnCancelarNovedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAgregarNovedad.dismiss();
            }
        });
    }

    public void poblarTipoNovedad()
    {
        objTipoNovedad.add(new TipoNovedadEntidad("0","SELECCIONE UNO"));
        objTipoNovedad.add(new TipoNovedadEntidad("34","34-RECOLECCION NO AUTORIZADA"));
        objTipoNovedad.add(new TipoNovedadEntidad("52","52-DIRECCION DE RECOLECCION ERRADA"));
        objTipoNovedad.add(new TipoNovedadEntidad("53","53-MERCANCIA NO ESTA LISTA"));
        objTipoNovedad.add(new TipoNovedadEntidad("54","54-EL CLIENTE NO TIENE CONOCIMIENTO DE LA RECOLECCION"));
        objTipoNovedad.add(new TipoNovedadEntidad("55","55-CUPO"));

        tipoNovedadAdapter = new ArrayAdapter<TipoNovedadEntidad>(this, android.R.layout.simple_spinner_dropdown_item, objTipoNovedad);
        spTipoNovedad.setAdapter(tipoNovedadAdapter);
    }

    public void guardarAgregarNovedad()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Grabando novedad...");
        progressDialog.show();

        AsyncHttpClient cliente = new ConfigurarCliente(new AsyncHttpClient(), getApplicationContext()).getCliente();

        RequestParams requestParams = new RequestParams();
        requestParams.add("accion","agregarNovedad");
        requestParams.add("usuarioCodigo", sharedPref.getString("usuarioCodigo", ""));
        requestParams.add("cencosCodigo", sharedPref.getString("cencosCodigo", ""));
        requestParams.add("ordenCargue", tvOrdenCargueCodigo.getText().toString());
        requestParams.add("tipnovCodigo", objTipoNovedad.get(spTipoNovedad.getSelectedItemPosition()).getTipoNovedadCodigo());
        requestParams.add("observacion", etObservacion.getText().toString());

        cliente.post("http://181.48.247.202/redetransmovil/apps/gestionarRecoleccion/gestionarRecoleccion.php", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                RespuestaRest respuestaRest = new RespuestaRest(response);

                if (respuestaRest.satisfactorio) {
                    Toast.makeText(getApplicationContext(), respuestaRest.mensaje, Toast.LENGTH_SHORT).show();
                    etObservacion.setText("");
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

    public void cerrarSesion(View view)
    {
        destruirSesion();
    }

    public void destruirSesion()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListaOrdenCargue.this);
        builder.setTitle("Cerrar sesión");
        builder.setMessage("¿Desea cerrar sesión?");

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SharedPreferences sharedPref = getSharedPreferences("DatosSesionRedetransMovil", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.clear().commit();

                Intent intent = new Intent(ListaOrdenCargue.this, Principal.class);
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