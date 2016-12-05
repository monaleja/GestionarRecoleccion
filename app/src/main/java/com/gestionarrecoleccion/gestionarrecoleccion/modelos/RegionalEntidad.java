package com.gestionarrecoleccion.gestionarrecoleccion.modelos;

/**
 * Created by Alejandra on 2016/12/04
 */

public class RegionalEntidad {
    String regionalCodigo;
    String regionalNombre;

    public RegionalEntidad(String regionalCodigo,String regionalNombre){
        this.regionalCodigo = regionalCodigo;
        this.regionalNombre = regionalNombre;
    }

    public String getRegionalCodigo() {
        return regionalCodigo;
    }

    public void setRegionalCodigo(String regionalCodigo) {
        this.regionalCodigo = regionalCodigo;
    }

    public String getRegionalNombre() {
        return regionalNombre;
    }

    public void setRegionalNombre(String regionalNombre) {
        this.regionalNombre = regionalNombre;
    }

    @Override
    public String toString() {
        return regionalNombre;
    }
}