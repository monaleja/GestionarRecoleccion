package com.gestionarrecoleccion.gestionarrecoleccion.entidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by root on 28/11/16.
 */

public class OrdenCargueEntidad {

    String planRecogidaCodigo;
    String ordenCargueCodigo;
    String remitenteNombre;
    String remitenteDireccion;
    String remitenteTelefono;
    String ordenCargueHora;
    JSONObject dataJsonObject;

    public OrdenCargueEntidad() {
    }

    public OrdenCargueEntidad(JSONObject jsonObject) {
        try {
            setDataJsonObject(jsonObject);
            setPlanRecogidaCodigo(jsonObject.getString("planrecogidda"));
            setOrdenCargueCodigo(jsonObject.getString("ordencargue"));
            setRemitenteNombre(jsonObject.getString("remitente_nombre"));
            setRemitenteDireccion(jsonObject.getString("remitente_direccion"));
            setRemitenteTelefono(jsonObject.getString("remitente_telefono"));
            setOrdenCargueHora(jsonObject.getString("hora"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getPlanRecogidaCodigo() {
        return planRecogidaCodigo;
    }

    public void setPlanRecogidaCodigo(String planRecogidaCodigo) {
        this.planRecogidaCodigo = planRecogidaCodigo;
    }

    public JSONObject getDataJsonObject() {
        return dataJsonObject;
    }

    public String getOrdenCargueHora() {
        return ordenCargueHora;
    }

    public void setOrdenCargueHora(String ordenCargueHora) {
        this.ordenCargueHora = ordenCargueHora;
    }

    public String getRemitenteTelefono() {
        return remitenteTelefono;
    }

    public void setRemitenteTelefono(String remitenteTelefono) {
        this.remitenteTelefono = remitenteTelefono;
    }

    public String getRemitenteDireccion() {
        return remitenteDireccion;
    }

    public void setRemitenteDireccion(String remitenteDireccion) {
        this.remitenteDireccion = remitenteDireccion;
    }

    public String getRemitenteNombre() {
        return remitenteNombre;
    }

    public void setRemitenteNombre(String remitenteNombre) {
        this.remitenteNombre = remitenteNombre;
    }

    public String getOrdenCargueCodigo() {
        return ordenCargueCodigo;
    }

    public void setOrdenCargueCodigo(String ordenCargueCodigo) {
        this.ordenCargueCodigo = ordenCargueCodigo;
    }

    public void setDataJsonObject(JSONObject dataJsonObject) {
        this.dataJsonObject = dataJsonObject;
    }

    public ArrayList<OrdenCargueEntidad> jsonArrayToArrayListOrdenCargue(JSONArray jsonArray) {
        ArrayList<OrdenCargueEntidad> arrayListOrdenCargue = new ArrayList<OrdenCargueEntidad>();

        for (int i= 0; i<jsonArray.length(); i++){
            try {
                arrayListOrdenCargue.add(new OrdenCargueEntidad((JSONObject) jsonArray.get(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return arrayListOrdenCargue;
    }


}
