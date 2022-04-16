package com.controldigital.app.service;

import java.util.List;

import com.controldigital.app.models.entity.Usuario;


public interface IUsuarioService {

	public List<Usuario> findall();

	public void save(Usuario usuario);

	public Usuario findOne(Long id);
	
	public Usuario findByEmail(String email);
	
	public Usuario findByResetPasswordToken(String token);

	public void delete(Long id);

	public List<Usuario> findUserByRole(String role);

	public List<Usuario> findByNumRegistro(String term);
}
