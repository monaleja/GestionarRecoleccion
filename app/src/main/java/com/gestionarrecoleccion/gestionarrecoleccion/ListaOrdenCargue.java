package com.gestionarrecoleccion.gestionarrecoleccion;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gestionarrecoleccion.gestionarrecoleccion.adapters.AdapterOrdenCargue;
import com.gestionarrecoleccion.gestionarrecoleccion.modelos.OrdenCargueEntidad;
import com.gestionarrecoleccion.gestionarrecoleccion.utils.RespuestaRest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListaOrdenCargue extends AppCompatActivity {
    ListView lvOrdenCargue;
    TextView tvUsuarioLogin;
    TextView tvUsuarioCencos;
    Dialog progressDialog;
    SharedPreferences sharedPref;
    ArrayList<OrdenCargueEntidad> ordenescargue = new ArrayList<OrdenCargueEntidad>();
    JSONArray ordenescargueJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_orden_cargue);
        initComponents();
        listarOrdenes();
    }

    public void initComponents(){
        lvOrdenCargue = (ListView)findViewById(R.id.lvOrdenCargue);
        sharedPref = getSharedPreferences("DatosSesionSilogtranMovil",Context.MODE_PRIVATE);
        /*tvUsuarioLogin = (TextView) findViewById(R.id.tvUsuarioLogin);
        tvUsuarioCencos = (TextView) findViewById(R.id.tvUsuarioCencos);

        tvUsuarioLogin.setText(sharedPref.getString("usuarioLogin", ""));
        tvUsuarioCencos.setText(sharedPref.getString("cencosNombre", ""));*/
    }

    public void listarOrdenes(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando ordenes de cargue...");
        progressDialog.show();

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        requestParams.add("accion","ListaOrdenCargue");
        requestParams.add("usuarioCodigo", sharedPref.getString("usuarioCodigo", ""));

        asyncHttpClient.post(getApplicationContext(), "http://181.48.247.202/redetransmovil/apps/gestionarRecoleccion/gestionarRecoleccion.php", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                RespuestaRest respuestaRest = new RespuestaRest(response);

                if (respuestaRest.satisfactorio) {
                    try {
                        ordenescargueJson = new JSONArray(respuestaRest.respuesta);

                        poblarListaOrdenCargue();

                        AdapterOrdenCargue adapterOrdencargue = new AdapterOrdenCargue(ListaOrdenCargue.this, ordenescargue);
                        lvOrdenCargue = (ListView) findViewById(R.id.lvOrdenCargue);
                        lvOrdenCargue.setAdapter(adapterOrdencargue);


                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "Algo salio mal, por favor contactar con el área de soporte.", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(getApplicationContext(), respuestaRest.mensaje, Toast.LENGTH_SHORT).show();
                }
            }

            /*public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                RespuestaRest respuestaRest = new RespuestaRest(response);

                if(respuestaRest.satisfactorio){
                    JSONArray data = respuestaRest.getDataJsonArray();

                    ArrayList<OrdenCargueEntidad> ordenCargue = new OrdenCargueEntidad().jsonArrayToArrayListOrdenCargue(data);
                    AdapterOrdenCargue adapterOrdenCargue = new AdapterOrdenCargue(getApplicationContext(), ordenCargue);

                    lvOrdenCargue.setAdapter(adapterOrdenCargue);
                    adapterOrdenCargue.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), respuestaRest.mensaje, Toast.LENGTH_SHORT).show();
                }

            }*/

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(getApplicationContext(), "Algo salio mal, por favor contactar con el área de soporte.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                progressDialog.dismiss();
            }
        });
    }

    public void poblarListaOrdenCargue(){
       for (int i = 0; i < ordenescargueJson.length(); i++) {
            try {
                JSONObject aplicacion = ordenescargueJson.getJSONObject(i);
                ordenescargue.add(
                        new OrdenCargueEntidad(
                                aplicacion.getString("planrecogida"),
                                aplicacion.getString("ordencargue"),
                                aplicacion.getString("remitente_nombre"),
                                aplicacion.getString("remitente_direccion"),
                                aplicacion.getString("remitente_telefono"),
                                aplicacion.getString("hora")
                        )
                );
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Algo salio mal, por favor contactar con el área de soporte.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
