package com.gestionarrecoleccion.gestionarrecoleccion.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alejandra on 27/11/2016.
 */

public class RespuestaRest {

    public String mensaje;
    public String respuesta;
    public boolean satisfactorio;

    public boolean isSatisfactorio() {
        return satisfactorio;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

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