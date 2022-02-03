package com.controldigital.app.models.dao;

import com.controldigital.app.models.entity.InfoAcademica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz con operaciones CRUD para objetos del tipo "InfoAcademica"
 */
public interface IInfoAcademicaDAO extends CrudRepository<InfoAcademica, Long> {

    /**
     * Consulta que encuentra la información académica de un usuario por el id del usuario
     * @param id
     * @return
     */
    @Query("select i from InfoAcademica i where i.users.id = ?1")
    InfoAcademica findInfoAcademicaByUserId(Long id);
}
