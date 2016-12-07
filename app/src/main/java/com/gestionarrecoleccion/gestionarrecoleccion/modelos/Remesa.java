package com.gestionarrecoleccion.gestionarrecoleccion.modelos;

/**
 * Created by equipo47 on 6/12/16.
 */

public class Remesa {

    private String remesaCodigo;
    private String peso;
    private String cantidad;
    private String ordcarCodigo;
    private String regdesCodigo;
    private String regdesNombre;

    public Remesa(String remesaCodigo, String peso, String cantidad, String ordcarCodigo, String regdesCodigo, String regdesNombre) {

        this.remesaCodigo = remesaCodigo;
        this.peso = peso;
        this.cantidad = cantidad;
        this.ordcarCodigo = ordcarCodigo;
        this.regdesCodigo = regdesCodigo;
        this.regdesNombre = regdesNombre;
    }

    public Remesa(){

    }

    public String getRemesaCodigo() {
        return remesaCodigo;
    }

    public void setRemesaCodigo(String remesaCodigo) {
        this.remesaCodigo = remesaCodigo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getOrdcarCodigo() {
        return ordcarCodigo;
    }

    public void setOrdcarCodigo(String ordcarCodigo) {
        this.ordcarCodigo = ordcarCodigo;
    }

    public String getRegdesCodigo() {
        return regdesCodigo;
    }

    public void setRegdesCodigo(String regdesCodigo) {
        this.regdesCodigo = regdesCodigo;
    }

    public String getRegdesNombre() {
        return regdesNombre;
    }

    public void setRegdesNombre(String regdesNombre) {
        this.regdesNombre = regdesNombre;
    }
}