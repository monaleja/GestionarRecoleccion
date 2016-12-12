package com.gestionarrecoleccion.gestionarrecoleccion.entidades;

/**
 * Created by equipo47 on 6/12/16.
 */

public class RemesaEntidad {

    private String remesaCodigo;
    private String peso;
    private String cantidad;
    private String ordcarCodigo;
    private String regdesCodigo;
    private String regdesNombre;
    private String plarecCodigo;

    public RemesaEntidad(String remesaCodigo, String peso, String cantidad, String ordcarCodigo, String regdesCodigo, String regdesNombre, String plarecCodigo) {

        this.remesaCodigo = remesaCodigo;
        this.peso = peso;
        this.cantidad = cantidad;
        this.ordcarCodigo = ordcarCodigo;
        this.regdesCodigo = regdesCodigo;
        this.regdesNombre = regdesNombre;
        this.plarecCodigo = plarecCodigo;
    }

    public RemesaEntidad(){

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

    public String getPlarecCodigo() {
        return plarecCodigo;
    }

    public void setPlarecCodigo(String plarecCodigo) {
        this.plarecCodigo = plarecCodigo;
    }

    @Override
    public String toString() {
        return "RemesaEntidad{" +
                "remesaCodigo='" + remesaCodigo + '\'' +
                ", peso='" + peso + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", ordcarCodigo='" + ordcarCodigo + '\'' +
                ", regdesCodigo='" + regdesCodigo + '\'' +
                ", regdesNombre='" + regdesNombre + '\'' +
                ", plarecCodigo='" + plarecCodigo + '\'' +
                '}';
    }
}