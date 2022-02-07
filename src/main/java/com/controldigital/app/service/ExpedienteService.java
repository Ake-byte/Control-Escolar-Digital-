package com.controldigital.app.service;

import com.controldigital.app.models.dao.IExpedienteDAO;
import com.controldigital.app.models.dao.IUsuarioDAO;
import com.controldigital.app.models.entity.Expediente;
import com.controldigital.app.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpedienteService implements IExpedienteService{

    @Autowired
    private IExpedienteDAO expedienteDAO;

    @Autowired
    private IUsuarioDAO usuarioDAO;

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
    @Transactional
    public void saveUsuario(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Expediente> findAll() {
        return (List<Expediente>) expedienteDAO.findAll();
    }
}
