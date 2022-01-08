package com.controldigital.app.models.dao;

import com.controldigital.app.models.entity.Expediente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IExpedienteDAO extends CrudRepository<Expediente, Long> {
    @Query("select e from Expediente e where e.users.id = ?1")
    Expediente findExpedienteByUserId(Long id);
}
