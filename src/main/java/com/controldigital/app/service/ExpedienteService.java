package com.controldigital.app.service;

import com.controldigital.app.models.dao.IExpedienteDAO;
import com.controldigital.app.models.entity.Expediente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpedienteService implements IExpedienteService{

    @Autowired
    private IExpedienteDAO expedienteDAO;

    @Override
    @Transactional(readOnly=true)
    public Expediente findExpedienteByUserId(Long id) {
        return expedienteDAO.findExpedienteByUserId(id);
    }

    @Override
    @Transactional
    public void save(Expediente expediente) {
        expedienteDAO.save(expediente);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Expediente> findAll() {
        return (List<Expediente>) expedienteDAO.findAll();
    }
}
