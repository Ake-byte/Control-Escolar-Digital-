package com.controldigital.app.service;

import com.controldigital.app.models.entity.InfoAcademica;

public interface IInfoAcademicaService {
    public InfoAcademica findInfoAcademicaByUserId(Long id);

    public void save(InfoAcademica infoAcademica);

    public void delete(Long id);
}
