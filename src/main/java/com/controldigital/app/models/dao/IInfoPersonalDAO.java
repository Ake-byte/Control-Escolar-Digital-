package com.controldigital.app.models.dao;

import com.controldigital.app.models.entity.InfoPersonal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IInfoPersonalDAO extends CrudRepository<InfoPersonal, Long> {
    @Query("select i from InfoPersonal i where i.users.id = ?1")
    InfoPersonal findInfoPersonalByUserId(Long id);
}
