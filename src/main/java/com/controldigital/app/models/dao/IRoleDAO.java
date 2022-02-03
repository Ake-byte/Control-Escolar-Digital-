package com.controldigital.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.controldigital.app.models.entity.Role;

import java.util.List;

/**
 * Interfaz con operaciones CRUD para objetos del tipo "Role"
 */
public interface IRoleDAO extends CrudRepository<Role, Long>{

    /**
     * Método que encuentra a un usuario según su rol
     * @param authority
     * @return
     */
    @Query("select r from Role r where r.authority like ?1%")
    public List<Role> findUsuarioByRole(String authority);

    /**
     * Método que encuentra un role por el campo de id de usuario
     * @param id
     * @return
     */
    @Query("select r from Role r where r.users.id = ?1")
    Role findRoleByUserId(Long id);
}
