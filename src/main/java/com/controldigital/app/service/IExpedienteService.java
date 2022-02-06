package com.controldigital.app.service;

import com.controldigital.app.models.entity.Expediente;

import java.util.List;

public interface IExpedienteService {
    Expediente findExpedienteByUserId(Long id);

    public void save(Expediente expediente);

    public void delete(Long id);

    public List<Expediente> findAll();
}
