package com.gestionarrecoleccion.gestionarrecoleccion;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.gestionarrecoleccion.gestionarrecoleccion.adapters.AdapterOrdenCargue;
import com.gestionarrecoleccion.gestionarrecoleccion.entidades.OrdenCargueEntidad;
import com.gestionarrecoleccion.gestionarrecoleccion.utils.RespuestaRest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListaOrdenCargue extends AppCompatActivity {
    ListView lvOrdenCargue;
    Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_orden_cargue);
        initComponents();
        listarOrdenes();
    }

    public void initComponents(){
        lvOrdenCargue = (ListView)findViewById(R.id.lvOrdenCargue);
    }

    public void listarOrdenes(){

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        requestParams.add("accion","ListaOrdenCargue");
        //requestParams.add("usuarioCodigo", );

        asyncHttpClient.post(getApplicationContext(), "http://181.48.247.202/redetransmovil/apps/gestionarRecoleccion/gestionarRecoleccion.php", requestParams, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

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

            }

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
}
