package com.controldigital.app.service;

import com.controldigital.app.models.dao.IExpedienteDAO;
import com.controldigital.app.models.entity.Expediente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpedienteService implements IExpedienteService{

    @Autowired
    private IExpedienteDAO expedienteDAO;

    @Override
    public Expediente findExpedienteByUserId(Long id) {
        return expedienteDAO.findExpedienteByUserId(id);
    }
}
