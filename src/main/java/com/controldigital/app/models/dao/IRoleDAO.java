package com.controldigital.app.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.controldigital.app.models.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Interfaz con operaciones CRUD para objetos del tipo "Role"
 */
public interface IRoleDAO extends PagingAndSortingRepository<Role, Long> {

    /**
     * Método que encuentra a un usuario según su rol
     * @param authority
     * @return
     */
    @Query("select r from Role r where r.authority like ?1%")
    public List<Role> findUsuarioByRole(String authority);

    @Query("select r from Role r where r.authority like ?1%"
        + " AND r.users.enabled = true")
    public Page<Role> findUsuarioByRolePage(String authority, Pageable pageable);

    @Query("select r from Role r where r.authority like ?1%"
            + " AND r.users.enabled = false")
    public Page<Role> findUsuarioByRolePageDisabled(String authority, Pageable pageable);

    /**
     * Método que encuentra un role por el campo de id de usuario
     * @param id
     * @return
     */
    @Query("select r from Role r where r.users.id = ?1")
    Role findRoleByUserId(Long id);
}
