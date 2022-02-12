package com.controldigital.app.service;

import com.controldigital.app.models.entity.InfoAcademica;
import com.controldigital.app.models.entity.Usuario;

public interface IInfoAcademicaService {
    public InfoAcademica findInfoAcademicaByUserId(Long id);

    public void save(InfoAcademica infoAcademica);

    public void saveUsuario(Usuario usuario);

    public void delete(Long id);

    public InfoAcademica findOne(Long id);
}
