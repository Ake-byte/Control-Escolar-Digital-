package com.controldigital.app.service;

import com.controldigital.app.models.dao.ISipDAO;
import com.controldigital.app.models.dao.IUsuarioDAO;
import com.controldigital.app.models.entity.SIP;
import com.controldigital.app.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SipService implements ISipService{

    @Autowired
    private ISipDAO sipDAO;

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    @Transactional(readOnly=true)
    public List<SIP> findUserSips(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void saveSip(SIP sip) {
        sipDAO.save(sip);
    }

    @Override
    @Transactional
    public void saveUsuario(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    @Transactional(readOnly=true)
    public SIP findOne(Long id) {
        return sipDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        sipDAO.deleteById(id);
    }


}
