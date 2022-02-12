package com.controldigital.app.service;

import com.controldigital.app.models.dao.IInfoAcademicaDAO;
import com.controldigital.app.models.dao.IUsuarioDAO;
import com.controldigital.app.models.entity.InfoAcademica;
import com.controldigital.app.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InfoAcademicaService implements IInfoAcademicaService{

    @Autowired
    private IInfoAcademicaDAO infoAcademicaDAO;

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    @Transactional(readOnly=true)
    public InfoAcademica findInfoAcademicaByUserId(Long id) {
        return infoAcademicaDAO.findInfoAcademicaByUserId(id);
    }

    @Override
    @Transactional
    public void save(InfoAcademica infoAcademica) {
        infoAcademicaDAO.save(infoAcademica);
    }

    @Override
    @Transactional
    public void saveUsuario(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        infoAcademicaDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public InfoAcademica findOne(Long id) {
        return infoAcademicaDAO.findById(id).orElse(null);
    }
}
