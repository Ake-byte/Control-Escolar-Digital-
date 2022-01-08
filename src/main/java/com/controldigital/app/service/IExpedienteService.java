package com.controldigital.app.service;

import com.controldigital.app.models.entity.Expediente;

public interface IExpedienteService {
    Expediente findExpedienteByUserId(Long id);
}
