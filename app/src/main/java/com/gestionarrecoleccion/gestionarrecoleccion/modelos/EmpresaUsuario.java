package com.gestionarrecoleccion.gestionarrecoleccion.modelos;

/**
 * Created by Alejandra on 27/11/2016.
 */
public class EmpresaUsuario {
    public String usuarioCodigo;
    public String usuarioLogin;

    public String empresaCodigo;
    public String empresaNombre;

    public String cencosCodigo;
    public String cencosNombre;

    public String usuarioImplementador;

    public EmpresaUsuario(String usuarioCodigo, String usuarioCodigoExterno, String usuarioLogin, String empresaCodigo, String empresaNombre, String cencosCodigo, String cencosNombre) {
        this.usuarioCodigo = usuarioCodigo;
        this.usuarioLogin = usuarioLogin;
        this.empresaCodigo = empresaCodigo;

        this.empresaNombre = empresaNombre;
        this.cencosCodigo = cencosCodigo;
        this.cencosNombre = cencosNombre;
        this.usuarioImplementador = usuarioImplementador;
    }

    public String getUsuarioCodigo() {
        return usuarioCodigo;
    }

    public void setUsuarioCodigo(String usuarioCodigo) {
        this.usuarioCodigo = usuarioCodigo;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getEmpresaCodigo() {
        return empresaCodigo;
    }

    public void setEmpresaCodigo(String empresaCodigo) {
        this.empresaCodigo = empresaCodigo;
    }

    public String getEmpresaNombre() {
        return empresaNombre;
    }

    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    public String getCencosCodigo() {
        return cencosCodigo;
    }

    public void setCencosCodigo(String cencosCodigo) {
        this.cencosCodigo = cencosCodigo;
    }

    public String getCencosNombre() {
        return cencosNombre;
    }

    public void setCencosNombre(String cencosNombre) {
        this.cencosNombre = cencosNombre;
    }

    public String getUsuarioImplementador() {
        return usuarioImplementador;
    }

    public void setUsuarioImplementador(String usuarioImplementador) {
        this.usuarioImplementador = usuarioImplementador;
    }
}