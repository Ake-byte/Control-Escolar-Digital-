package com.controldigital.app.service;


import com.controldigital.app.models.dao.IInfoPersonalDAO;
import com.controldigital.app.models.entity.InfoPersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InforPersonalService implements IInfoPersonalService{

    @Autowired
    private IInfoPersonalDAO infoPersonalDAO;

    @Override
    @Transactional(readOnly=true)
    public InfoPersonal findInfoPersonalByUserId(Long id) {
        return infoPersonalDAO.findInfoPersonalByUserId(id);
    }
}
