package com.controldigital.app.service;

import com.controldigital.app.models.entity.InfoAcademica;

public interface IInfoAcademicaService {
    InfoAcademica findInfoAcademicaByUserId(Long id);
}
