package com.gestionarrecoleccion.gestionarrecoleccion.entidades;

import android.content.ContentValues;

import com.gestionarrecoleccion.gestionarrecoleccion.bd.NovedadOrdenCargueEsquema;

/**
 * Created by Alejandra on 19/12/2016.
 */

public class NovedadOrdenCargueEntidad {

    String novedadCodigo;
    String ordenCargueCodigo;
    String novedadObservacion;
    String tipoNovedadCodigo;
    String novedadFechaCreacion;
    String novedadHoraCreacion;
    String novedadUsuarioCodigo;
    String novedadSincronizado;

    public NovedadOrdenCargueEntidad(String novedadCodigo, String ordenCargueCodigo, String novedadObservacion, String tipoNovedadCodigo, String novedadFechaCreacion, String novedadHoraCreacion, String novedadUsuarioCodigo, String novedadSincronizado) {
        this.novedadCodigo = novedadCodigo;
        this.ordenCargueCodigo = ordenCargueCodigo;
        this.novedadObservacion = novedadObservacion;
        this.tipoNovedadCodigo = tipoNovedadCodigo;
        this.novedadFechaCreacion = novedadFechaCreacion;
        this.novedadHoraCreacion = novedadHoraCreacion;
        this.novedadUsuarioCodigo = novedadUsuarioCodigo;
        this.novedadSincronizado = novedadSincronizado;
    }

    public String getNovedadCodigo() {
        return novedadCodigo;
    }

    public void setNovedadCodigo(String novedadCodigo) {
        this.novedadCodigo = novedadCodigo;
    }

    public String getOrdenCargueCodigo() {
        return ordenCargueCodigo;
    }

    public void setOrdenCargueCodigo(String ordenCargueCodigo) {
        this.ordenCargueCodigo = ordenCargueCodigo;
    }

    public String getNovedadObservacion() {
        return novedadObservacion;
    }

    public void setNovedadObservacion(String novedadObservacion) {
        this.novedadObservacion = novedadObservacion;
    }

    public String getTipoNovedadCodigo() {
        return tipoNovedadCodigo;
    }

    public void setTipoNovedadCodigo(String tipoNovedadCodigo) {
        this.tipoNovedadCodigo = tipoNovedadCodigo;
    }

    public String getNovedadFechaCreacion() {
        return novedadFechaCreacion;
    }

    public void setNovedadFechaCreacion(String novedadFechaCreacion) {
        this.novedadFechaCreacion = novedadFechaCreacion;
    }

    public String getNovedadHoraCreacion() {
        return novedadHoraCreacion;
    }

    public void setNovedadHoraCreacion(String novedadHoraCreacion) {
        this.novedadHoraCreacion = novedadHoraCreacion;
    }

    public String getNovedadUsuarioCodigo() {
        return novedadUsuarioCodigo;
    }

    public void setNovedadUsuarioCodigo(String novedadUsuarioCodigo) {
        this.novedadUsuarioCodigo = novedadUsuarioCodigo;
    }

    public String getNovedadSincronizado() {
        return novedadSincronizado;
    }

    public void setNovedadSincronizado(String novedadSincronizado) {
        this.novedadSincronizado = novedadSincronizado;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(NovedadOrdenCargueEsquema.NovedadOrdenCargue.novedadCodigo, novedadCodigo);
        values.put(NovedadOrdenCargueEsquema.NovedadOrdenCargue.ordenCargueCodigo, ordenCargueCodigo);
        values.put(NovedadOrdenCargueEsquema.NovedadOrdenCargue.novedadObservacion, novedadObservacion);
        values.put(NovedadOrdenCargueEsquema.NovedadOrdenCargue.tipoNovedadCodigo, tipoNovedadCodigo);
        values.put(NovedadOrdenCargueEsquema.NovedadOrdenCargue.novedadFechaCreacion, novedadFechaCreacion);
        values.put(NovedadOrdenCargueEsquema.NovedadOrdenCargue.novedadHoraCreacion, novedadHoraCreacion);
        values.put(NovedadOrdenCargueEsquema.NovedadOrdenCargue.novedadUsuarioCodigo, novedadUsuarioCodigo);
        values.put(NovedadOrdenCargueEsquema.NovedadOrdenCargue.novedadSincronizado, novedadSincronizado);
        return values;
    }
}