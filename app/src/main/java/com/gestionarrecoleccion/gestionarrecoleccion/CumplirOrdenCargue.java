package com.gestionarrecoleccion.gestionarrecoleccion;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.gestionarrecoleccion.gestionarrecoleccion.modelos.OrdenCargueEntidad;
import com.gestionarrecoleccion.gestionarrecoleccion.modelos.RegionalEntidad;

import java.util.ArrayList;

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
    Button btnGuardarCumplido;
    Button btnCancelarCumplido;
    ImageView ivCerrarSesion;
    SharedPreferences sharedPref;
    ArrayList<OrdenCargueEntidad> arrayOrdenescargue = new ArrayList<OrdenCargueEntidad>();
    ArrayList<RegionalEntidad> objRegional = new ArrayList<RegionalEntidad>();
    ArrayAdapter<RegionalEntidad> regionalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cumplir_ordencargue);
        initComponents();
    }

    public void initComponents(){
        sharedPref = getSharedPreferences("DatosSesionRedetransMovil",Context.MODE_PRIVATE);
        tvUsuarioLogin = (TextView) findViewById(R.id.tvUsuarioLogin);
        tvUsuarioCentrocosto = (TextView) findViewById(R.id.tvUsuarioCentrocosto);
        tvUsuarioLogin.setText(sharedPref.getString("usuarioLogin", ""));
        tvUsuarioCentrocosto.setText(sharedPref.getString("cencosNombre", ""));
        ivCerrarSesion = (ImageView) findViewById(R.id.imageView);
        tvOrdenCargueCodigo = (TextView) findViewById(R.id.tvOrdenCargueCodigo);
        etRemesa = (EditText) findViewById(R.id.etRemesa);
        etPeso = (EditText) findViewById(R.id.etPeso);
        etCantidad = (EditText) findViewById(R.id.etCantidad);
        spRegionalDestino = (Spinner) findViewById(R.id.spRegionalDestino);
        btnGuardarCumplido = (Button) findViewById(R.id.btnGuardarCumplido);
        btnCancelarCumplido = (Button) findViewById(R.id.btnCancelarCumplido);
        poblarRegional();
    }

    public void poblarRegional(){
        objRegional.add(new RegionalEntidad("0","SELECCIONE UNO"));
        objRegional.add(new RegionalEntidad("1","CENTRO"));
        objRegional.add(new RegionalEntidad("2","ANTIOQUIA"));
        objRegional.add(new RegionalEntidad("3","OCCIDENTE"));
        objRegional.add(new RegionalEntidad("4","EJE CAFETERO"));
        objRegional.add(new RegionalEntidad("5","SANTANDER SUR"));
        objRegional.add(new RegionalEntidad("6","COSTA NORTE"));
        objRegional.add(new RegionalEntidad("7","TOLIMA GRANDE"));
        objRegional.add(new RegionalEntidad("8","COSTA BAJA PLANETA"));
        objRegional.add(new RegionalEntidad("9","SANTANDER NORTE"));
        objRegional.add(new RegionalEntidad("10","COSTA CARIBE"));
        objRegional.add(new RegionalEntidad("13","COSTA MEDIA SINCELEJO"));
        objRegional.add(new RegionalEntidad("25","NACIONAL PRINCIPAL"));

        regionalAdapter = new ArrayAdapter<RegionalEntidad>(this, android.R.layout.simple_spinner_dropdown_item, objRegional);
        spRegionalDestino.setAdapter(regionalAdapter);
    }

    public void cancelarCumplido(View view){
        btnCancelarCumplido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CumplirOrdenCargue.this, ListaOrdenCargue.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public void cerrarSesion(View view)
    {
        destruirSesion();
    }

    public void destruirSesion() {
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
