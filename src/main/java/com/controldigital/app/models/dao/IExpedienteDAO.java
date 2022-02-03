package com.controldigital.app.models.dao;

import com.controldigital.app.models.entity.Expediente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz con operaciones CRUD para objetos del tipo "Expediente"
 */
public interface IExpedienteDAO extends CrudRepository<Expediente, Long> {

    /**
     * Consulta que devuelve el expdiente correspondiente al id del usuario
     * @param id
     * @return
     */
    @Query("select e from Expediente e where e.users.id = ?1")
    Expediente findExpedienteByUserId(Long id);
}
