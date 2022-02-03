package com.controldigital.app.models.dao;

import com.controldigital.app.models.entity.SIP;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz con operaciones CRUD para objetos del tipo "SIP"
 */
public interface ISipDAO extends CrudRepository<SIP, Long> {
}
