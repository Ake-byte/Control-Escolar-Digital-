package com.controldigital.app.util;

import com.controldigital.app.models.entity.Expediente;
import com.controldigital.app.models.entity.InfoAcademica;
import com.controldigital.app.models.entity.InfoPersonal;
import com.controldigital.app.models.entity.Usuario;

public class UserDetails {
    private Usuario usuario;
    private InfoPersonal infoPersonal;
    private InfoAcademica infoAcademica;
    private Expediente expediente;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public InfoPersonal getInfoPersonal() {
        return infoPersonal;
    }

    public void setInfoPersonal(InfoPersonal infoPersonal) {
        this.infoPersonal = infoPersonal;
    }

    public InfoAcademica getInfoAcademica() {
        return infoAcademica;
    }

    public void setInfoAcademica(InfoAcademica infoAcademica) {
        this.infoAcademica = infoAcademica;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }
}
