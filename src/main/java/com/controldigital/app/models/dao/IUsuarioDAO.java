package com.controldigital.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.controldigital.app.models.entity.Usuario;

import java.util.List;

/**
 * Interfaz con operaciones CRUD para objetos del tipo "Usuario"
 */
public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{

	/**
	 * Método que encuentra al usuario por su dirección de correo electrónico
	 * @param email
	 * @return
	 */
	public Usuario findByEmail(String email);

	/**
	 * Método que encuentra al usuario por el token creado cuando olvida su contraseña
	 * @param token
	 * @return
	 */
	public Usuario findByResetPasswordToken(String token);

	/**
	 * Método que devuelve una lista de usuarios según sea el rol especificado
	 * @param role
	 * @return
	 */
	@Query("select u from Usuario u where u.roles.authority like ?1%")
	public List<Usuario> findUserByRole(String role);

	/**
	 * Método que de acuerdo a los parámetros insertados, devuelve a los usuarios que coincidan con base en
	 * el número de registro
	 * @param term
	 * @return
	 */
	@Query("select u from Usuario u where u.expediente.numeroRegistro LIKE %?1%")
	public List<Usuario> findByNumRegistro(String term);
}
