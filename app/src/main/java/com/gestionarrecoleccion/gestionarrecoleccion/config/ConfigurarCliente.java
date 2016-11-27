package com.gestionarrecoleccion.gestionarrecoleccion.config;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by Alejandra on 26/11/2016.
 */
public class ConfigurarCliente {
    AsyncHttpClient asyncHttpClient;

    public ConfigurarCliente(AsyncHttpClient cliente, Context context)
    {
        this.asyncHttpClient = cliente;
        asyncHttpClient.setMaxRetriesAndTimeout(2, 300000);
    }

    public AsyncHttpClient getCliente()
    {
        return this.asyncHttpClient;
    }
}
