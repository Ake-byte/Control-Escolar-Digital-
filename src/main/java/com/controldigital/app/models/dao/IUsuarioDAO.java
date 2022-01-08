package com.controldigital.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.controldigital.app.models.entity.Usuario;

import java.util.List;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{

	public Usuario findByEmail(String email);

	public Usuario findByResetPasswordToken(String token);

	@Query("select u from Usuario u where u.roles.authority like ?1%")
	public List<Usuario> findUserByRole(String role);
}
