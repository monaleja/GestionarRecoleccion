package com.gestionarrecoleccion.gestionarrecoleccion.entidades;

/**
 * Created by Alejandra on 2016/01/01.
 */
public class Par {
    String llave;
    String valor;

    public Par(String llave, String valor) {
        this.llave = llave;
        this.valor = valor;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
