package com.controldigital.app.service;

import com.controldigital.app.models.entity.Usuario;
import com.controldigital.app.util.Informes;

import java.util.List;

public interface IInformesService {

    public List<Usuario> findAlumnosBy(Informes informes);
}
