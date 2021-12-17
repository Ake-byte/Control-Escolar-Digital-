package com.controldigital.app.service;

import com.controldigital.app.models.entity.Role;
import com.controldigital.app.util.Informes;

import java.util.List;

public interface IInformesService {

    public List<Role> findAlumnosBy(Informes informes);
}
