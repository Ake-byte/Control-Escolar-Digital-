package com.controldigital.app.models.entity;

public class UserDetails {
    private Usuario usuario;
    private InfoAcademica infoAcademica;
    private InfoPersonal infoPersonal;
    private Expediente expediente;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public InfoAcademica getInfoAcademica() {
        return infoAcademica;
    }

    public void setInfoAcademica(InfoAcademica infoAcademica) {
        this.infoAcademica = infoAcademica;
    }

    public InfoPersonal getInfoPersonal() {
        return infoPersonal;
    }

    public void setInfoPersonal(InfoPersonal infoPersonal) {
        this.infoPersonal = infoPersonal;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }
}
