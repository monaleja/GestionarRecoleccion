package com.gestionarrecoleccion.gestionarrecoleccion;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gestionarrecoleccion.gestionarrecoleccion.adapters.AdapterOrdenCargue;
import com.gestionarrecoleccion.gestionarrecoleccion.config.ConfigurarCliente;
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
    TextView tvUsuarioCentrocosto;
    JSONArray ordenescargueJson;
    SharedPreferences sharedPref;
    ArrayList<OrdenCargueEntidad> ordenescargue = new ArrayList<OrdenCargueEntidad>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_orden_cargue);
        initComponents();
        listarOrdenes();
    }

    public void initComponents(){
        lvOrdenCargue = (ListView)findViewById(R.id.lvOrdenCargue);
        sharedPref = getSharedPreferences("DatosSesionRedetransMovil",Context.MODE_PRIVATE);
        tvUsuarioLogin = (TextView) findViewById(R.id.tvUsuarioLogin);
        tvUsuarioCentrocosto = (TextView) findViewById(R.id.tvUsuarioCentrocosto);
        tvUsuarioLogin.setText(sharedPref.getString("usuarioLogin", ""));
        tvUsuarioCentrocosto.setText(sharedPref.getString("cencosNombre", ""));
    }

    public void listarOrdenes(){
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

    public void poblarListaOrdenCargue(){

       for (int i = 0; i < ordenescargueJson.length(); i++) {
            try {
                JSONObject ordencargue = ordenescargueJson.getJSONObject(i);
                ordenescargue.add(new OrdenCargueEntidad(
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
}