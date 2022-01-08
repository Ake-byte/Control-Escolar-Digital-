package com.controldigital.app.models.dao;

import com.controldigital.app.models.entity.InfoAcademica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IInfoAcademicaDAO extends CrudRepository<InfoAcademica, Long> {
    @Query("select i from InfoAcademica i where i.users.id = ?1")
    InfoAcademica findInfoAcademicaByUserId(Long id);
}
