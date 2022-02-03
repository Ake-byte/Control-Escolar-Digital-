package com.controldigital.app.models.dao;

import com.controldigital.app.models.entity.InfoPersonal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz con operaciones CRUD para objetos del tipo "InfoPersonal"
 */
public interface IInfoPersonalDAO extends CrudRepository<InfoPersonal, Long> {

    /**
     * Consulta que encuentra la información personal de un usuario por el id del usuario
     * @param id
     * @return
     */
    @Query("select i from InfoPersonal i where i.users.id = ?1")
    InfoPersonal findInfoPersonalByUserId(Long id);
}
