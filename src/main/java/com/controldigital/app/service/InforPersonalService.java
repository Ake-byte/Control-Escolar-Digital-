package com.controldigital.app.service;


import com.controldigital.app.models.dao.IInfoPersonalDAO;
import com.controldigital.app.models.dao.IUsuarioDAO;
import com.controldigital.app.models.entity.InfoPersonal;
import com.controldigital.app.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InforPersonalService implements IInfoPersonalService{

    @Autowired
    private IInfoPersonalDAO infoPersonalDAO;

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    @Transactional(readOnly=true)
    public InfoPersonal findInfoPersonalByUserId(Long id) {
        return infoPersonalDAO.findInfoPersonalByUserId(id);
    }

    @Override
    @Transactional(readOnly=true)
    public InfoPersonal findOne(Long id) {
        return infoPersonalDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(InfoPersonal infoPersonal) {
        infoPersonalDAO.save(infoPersonal);
    }

    @Override
    @Transactional
    public void saveUsuario(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        infoPersonalDAO.deleteById(id);
    }
}
