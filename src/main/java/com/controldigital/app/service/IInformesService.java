package com.controldigital.app.service;

import com.controldigital.app.models.entity.Usuario;
import com.controldigital.app.util.Informes;
import com.controldigital.app.util.UserDetails;

import java.util.List;

public interface IInformesService {

    public List<UserDetails> findAlumnosBy(Informes informes, List<UserDetails> userDetailsList);
}
