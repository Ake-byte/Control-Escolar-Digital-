package com.controldigital.app.service;

import com.controldigital.app.models.entity.SIP;
import com.controldigital.app.models.entity.Usuario;

import java.util.List;

public interface ISipService {
    public List<SIP> findUserSips(Long id);
    public void saveSip(SIP sip);
    public void saveUsuario(Usuario usuario);
    public SIP findOne(Long id);
    public void delete(Long id);
}
