package com.controldigital.app.service;

import com.controldigital.app.models.dao.IInfoAcademicaDAO;
import com.controldigital.app.models.entity.InfoAcademica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InfoAcademicaService implements IInfoAcademicaService{

    @Autowired
    private IInfoAcademicaDAO infoAcademicaDAO;

    @Override
    @Transactional(readOnly=true)
    public InfoAcademica findInfoAcademicaByUserId(Long id) {
        return infoAcademicaDAO.findInfoAcademicaByUserId(id);
    }

    @Override
    public void save(InfoAcademica infoAcademica) {
        infoAcademicaDAO.save(infoAcademica);
    }
}
