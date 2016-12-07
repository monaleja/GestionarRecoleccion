package com.gestionarrecoleccion.gestionarrecoleccion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by Alejandra on 04/12/2016.
 */
public class EscanearCodigoDeBarras extends AppCompatActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZBarScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        /*Intent intent = new Intent(EscanearCodigoDeBarras.this, CumplirOrdenCargue.class);
        intent.putExtra("valorCodigoDeBarras", result.getContents());
        intent.putExtra("tipoCodigoDeBarras", result.getBarcodeFormat().getName());*/

        SharedPreferences sharedPref = getSharedPreferences("DatosSesionRedetransMovil", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("valorCodigoDeBarras", result.getContents());
        editor.commit();

        mScannerView.stopCamera();
        onBackPressed();

        //startActivity(intent);
    }
}