package com.controldigital.app.service;

import com.controldigital.app.models.entity.Expediente;
import com.controldigital.app.models.entity.Usuario;

import java.util.List;

public interface IExpedienteService {
    Expediente findExpedienteByUserId(Long id);

    public void save(Expediente expediente);

    public void saveUsuario(Usuario usuario);

    public void delete(Long id);

    public List<Expediente> findAll();
}
