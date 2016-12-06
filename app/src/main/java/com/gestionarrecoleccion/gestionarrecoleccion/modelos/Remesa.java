package com.gestionarrecoleccion.gestionarrecoleccion.modelos;

/**
 * Created by equipo47 on 6/12/16.
 */

public class Remesa {

    private String codigo;
    private String peso;
    private String cantidad;
    private String ordcarcodigo;
    private String regdescodigo;
    private String regdesnombre;

    public Remesa(String codigo, String peso, String cantidad, String ordcarcodigo, String regdescodigo, String regdesnombre) {

        this.codigo = codigo;
        this.peso = peso;
        this.cantidad = cantidad;
        this.ordcarcodigo = ordcarcodigo;
        this.regdescodigo = regdescodigo;
        this.regdesnombre = regdesnombre;
    }

    public Remesa(){

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getOrdcarcodigo() {
        return ordcarcodigo;
    }

    public void setOrdcarcodigo(String ordcarcodigo) {
        this.ordcarcodigo = ordcarcodigo;
    }

    public String getRegdescodigo() {
        return regdescodigo;
    }

    public void setRegdescodigo(String regdescodigo) {
        this.regdescodigo = regdescodigo;
    }

    public String getRegdesnombre() {
        return regdesnombre;
    }

    public void setRegdesnombre(String regdesnombre) {
        this.regdesnombre = regdesnombre;
    }
}