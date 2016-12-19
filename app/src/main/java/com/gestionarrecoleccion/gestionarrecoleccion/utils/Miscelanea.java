package com.gestionarrecoleccion.gestionarrecoleccion.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;


/**
 * Created by Alejandra on 27/11/2016
 */
public class Miscelanea {

    public static void verificarConexion(final Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (!(activeNetwork != null && activeNetwork.isConnectedOrConnecting())) {
            showToastConnectionFailure(activity.getApplicationContext());
        } else {
            AsyncHttpClient client = new AsyncHttpClient();

            client.get(activity.getApplicationContext(), "http://181.48.247.202/redetransmovil/", new TextHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if(!responseString.equals("RedetransMovil")){
                        showToastConnectionFailure(activity.getApplicationContext());
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    showToastConnectionFailure(activity.getApplicationContext());
                }
            });
        }
    }

    public static void showToastConnectionFailure(Context context)
    {
        Toast.makeText(context, "La aplicación requiere de una conexión a internet, por favor verifique el estado de su red. Si tiene una red de datos móviles, verifique su disponibilidad.", Toast.LENGTH_LONG).show();
    }

    public static boolean verificarInternet(final Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (!(activeNetwork != null && activeNetwork.isConnectedOrConnecting())) {
            return false;
        }else{
            return true;
        }
    }
}