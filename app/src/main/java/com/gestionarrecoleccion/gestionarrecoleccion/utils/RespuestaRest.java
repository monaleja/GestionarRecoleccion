package com.gestionarrecoleccion.gestionarrecoleccion.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alejandra on 27/11/2016.
 */
public class RespuestaRest {
    public boolean satisfactorio;
    public String respuesta;
    public String mensaje;

    public RespuestaRest(JSONObject jsonObject)
    {
        try {
            this.satisfactorio = jsonObject.getBoolean("satisfactorio");
            this.respuesta = jsonObject.getString("respuesta");
            this.mensaje = jsonObject.getString("mensaje");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}