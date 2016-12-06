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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gestionarrecoleccion.gestionarrecoleccion.adapters.AdapterListaRemesa;
import com.gestionarrecoleccion.gestionarrecoleccion.adapters.AdapterOrdenCargue;
import com.gestionarrecoleccion.gestionarrecoleccion.modelos.OrdenCargueEntidad;
import com.gestionarrecoleccion.gestionarrecoleccion.modelos.RegionalEntidad;
import com.gestionarrecoleccion.gestionarrecoleccion.modelos.Remesa;

import java.util.ArrayList;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by Alejandra on 03/12/2016.
 */
public class CumplirOrdenCargue extends AppCompatActivity /*implements ZBarScannerView.ResultHandler*/ {
    TextView tvUsuarioLogin;
    TextView tvUsuarioCentrocosto;
    TextView tvOrdenCargue;
    EditText etRemesa;
    EditText etPeso;
    EditText etCantidad;
    Spinner spRegionalDestino;
    ImageView ivCerrarSesion;
    SharedPreferences sharedPref;
    ArrayList<OrdenCargueEntidad> arrayOrdenescargue = new ArrayList<OrdenCargueEntidad>();
    ArrayList<RegionalEntidad> objRegional = new ArrayList<RegionalEntidad>();
    ArrayList<Remesa> arrayRemesa;
    ArrayAdapter<RegionalEntidad> regionalAdapter;
    ZBarScannerView mScannerView;
    String valorCodigoDeBarras;
    String tipoCodigoDeBarras;
    ListView listRemesas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cumplir_ordencargue);
        initComponents();

        Intent intent = getIntent();
        valorCodigoDeBarras = intent.getStringExtra("valorCodigoDeBarras");
        tipoCodigoDeBarras = intent.getStringExtra("tipoCodigoDeBarras");

        etRemesa = (EditText) findViewById(R.id.etRemesa);
        etRemesa.setText(valorCodigoDeBarras);
    }

    public void initComponents()
    {
        sharedPref = getSharedPreferences("DatosSesionRedetransMovil",Context.MODE_PRIVATE);
        tvUsuarioLogin = (TextView) findViewById(R.id.tvUsuarioLogin);
        tvUsuarioLogin.setText(sharedPref.getString("usuarioLogin", ""));
        tvUsuarioCentrocosto = (TextView) findViewById(R.id.tvUsuarioCentrocosto);
        tvUsuarioCentrocosto.setText(sharedPref.getString("cencosNombre", ""));
        ivCerrarSesion = (ImageView) findViewById(R.id.imageView);
        tvOrdenCargue = (TextView) findViewById(R.id.tvOrdenCargue);
        etPeso = (EditText) findViewById(R.id.etPeso);
        etCantidad = (EditText) findViewById(R.id.etCantidad);
        spRegionalDestino = (Spinner) findViewById(R.id.spRegionalDestino);
        poblarRegional();

        arrayRemesa = new ArrayList<Remesa>();
    }

    public void poblarRegional()
    {
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

    public void escanearCodigoDeBarras(View view)
    {
        Intent intent = new Intent(CumplirOrdenCargue.this, EscanearCodigoDeBarras.class);
        startActivity(intent);
        /*mScannerView = new ZBarScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();*/
    }

    /*@Override
    public void handleResult(Result result) {
        valorCodigoDeBarras = result.getContents();
        tipoCodigoDeBarras = result.getBarcodeFormat().getName();

        etRemesa.setText(valorCodigoDeBarras);
        mScannerView.stopCamera();
        mScannerView.destroyDrawingCache();
    }*/

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



    public void AdicionarRemesa(View view)
    {
        Toast.makeText(getApplicationContext(), "Estoy Adicionado la Remesa", Toast.LENGTH_SHORT).show();

        RegionalEntidad regional = (RegionalEntidad)spRegionalDestino.getSelectedItem();

        arrayRemesa.add(new Remesa(
                etRemesa.getText().toString(),
                etPeso.getText().toString(),
                etCantidad.getText().toString(),
                "0101000001",
                regional.getRegionalCodigo(),
                regional.getRegionalNombre()
                ));

        AdapterListaRemesa adapterlistaremesa = new AdapterListaRemesa(CumplirOrdenCargue.this, arrayRemesa);
        listRemesas = (ListView) findViewById(R.id.lvRemesas);
        listRemesas.setAdapter(adapterlistaremesa);
    }
}
