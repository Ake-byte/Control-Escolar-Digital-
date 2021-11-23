package com.controldigital.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.controldigital.app.models.entity.Role;

import java.util.List;

public interface IRoleDAO extends CrudRepository<Role, Long>{

    @Query("select r from Role r where r.authority like ?1%")
    public List<Role> findUsuarioByRole(String authority);

    @Query("select r from Role r where r.users.id = ?1")
    Role findRoleByUserId(Long id);
}
