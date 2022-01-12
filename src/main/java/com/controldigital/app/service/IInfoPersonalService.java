package com.controldigital.app.service;

import com.controldigital.app.models.entity.InfoPersonal;

public interface IInfoPersonalService {
    InfoPersonal findInfoPersonalByUserId(Long id);

    public void save(InfoPersonal infoPersonal);
}
