package com.gestionarrecoleccion.gestionarrecoleccion;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gestionarrecoleccion.gestionarrecoleccion.config.ConfigurarCliente;
import com.gestionarrecoleccion.gestionarrecoleccion.utils.RespuestaRest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class Principal extends ActionBarActivity {

    EditText etUsuario;
    EditText etClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.initComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.initComponents();
    }

    private void initComponents() {
        validarSesion();

        /* Mantener fuente por defecto, ya que si se define el edittext de tipo password cambia la fuente del hint */
        etClave = (EditText) findViewById(R.id.etClave);
        etClave.setTypeface(Typeface.DEFAULT);
        etClave.setTransformationMethod(new PasswordTransformationMethod());

        etUsuario = (EditText) findViewById(R.id.etUsuario);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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

    public void getEmpresas(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Iniciando sesión...");
        progressDialog.show();

        AsyncHttpClient cliente = new ConfigurarCliente(new AsyncHttpClient(), getApplicationContext()).getCliente();

        RequestParams requestParams = new RequestParams();
        //requestParams.add("accion", "login");
        requestParams.add("usuario", etUsuario.getText().toString());
        requestParams.add("clave", etClave.getText().toString());

        cliente.post("http://181.48.247.202/redetransmovil/usuario.php", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                RespuestaRest respuestaRest = new RespuestaRest(response);
                if(respuestaRest.satisfactorio){
                    Intent intent = new Intent(Principal.this, SeleccionarEmpresa.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("empresasJson", respuestaRest.respuesta);
                    startActivity(intent);
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

    public void validarSesion()
    {
        SharedPreferences sharedPref = getSharedPreferences("DatosSesionSilogtranMovil", Context.MODE_WORLD_READABLE);

        String usuarioLogin = sharedPref.getString("usuarioLogin", "");
        if(!usuarioLogin.equals("")){
            Intent intent = new Intent(Principal.this, Principal.class);
            //Intent intent = new Intent(Login.this, MenuAplicaciones.class);
            startActivity(intent);
        }
    }
}