package com.controldigital.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.controldigital.app.models.entity.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{

	public Usuario findByEmail(String email);

	public Usuario findByResetPasswordToken(String token);
}
