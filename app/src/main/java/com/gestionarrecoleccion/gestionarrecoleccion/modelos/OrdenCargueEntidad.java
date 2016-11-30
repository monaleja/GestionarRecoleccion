package com.gestionarrecoleccion.gestionarrecoleccion.modelos;

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

    public OrdenCargueEntidad(String planRecogidaCodigo, String ordenCargueCodigo, String remitenteNombre, String remitenteDireccion, String remitenteTelefono, String ordenCargueHora) {
        this.planRecogidaCodigo = planRecogidaCodigo;
        this.ordenCargueCodigo = ordenCargueCodigo;
        this.remitenteNombre = remitenteNombre;
        this.remitenteDireccion = remitenteDireccion;
        this.remitenteTelefono = remitenteTelefono;
        this.ordenCargueHora = ordenCargueHora;
    }

    public String getPlanRecogidaCodigo() {
        return planRecogidaCodigo;
    }

    public void setPlanRecogidaCodigo(String planRecogidaCodigo) {
        this.planRecogidaCodigo = planRecogidaCodigo;
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
}
