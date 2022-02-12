package com.controldigital.app.service;

import com.controldigital.app.models.entity.InfoPersonal;
import com.controldigital.app.models.entity.Usuario;

public interface IInfoPersonalService {
    InfoPersonal findInfoPersonalByUserId(Long id);

    public InfoPersonal findOne(Long id);

    public void save(InfoPersonal infoPersonal);

    public void saveUsuario(Usuario usuario);

    public void delete(Long id);
}
