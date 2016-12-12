package com.gestionarrecoleccion.gestionarrecoleccion.entidades;

/**
 * Created by Alejandra on 02/12/2016.
 */

public class TipoNovedadEntidad {
    String tipoNovedadCodigo;
    String tipoNovedadNombre;

    public TipoNovedadEntidad(String tipoNovedadCodigo,String tipoNovedadNombre){
        this.tipoNovedadCodigo = tipoNovedadCodigo;
        this.tipoNovedadNombre = tipoNovedadNombre;
    }

    public String getTipoNovedadCodigo() {
        return tipoNovedadCodigo;
    }

    public void setTipoNovedadCodigo(String tipoNovedadCodigo) {
        this.tipoNovedadCodigo = tipoNovedadCodigo;
    }

    public String getTipoNovedadNombre() {
        return tipoNovedadNombre;
    }

    public void setTipoNovedadNombre(String tipoNovedadNombre) {
        this.tipoNovedadNombre = tipoNovedadNombre;
    }

    @Override
    public String toString() {
        return tipoNovedadNombre;
    }
}