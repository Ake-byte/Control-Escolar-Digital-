package com.controldigital.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.controldigital.app.models.dao.IUsuarioDAO;
import com.controldigital.app.models.entity.Role;
import com.controldigital.app.models.entity.Usuario;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioDAO.findByEmail(email);
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		Role role = usuario.getRoles();	
		authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		
		return new User(usuario.getEmail(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

}
