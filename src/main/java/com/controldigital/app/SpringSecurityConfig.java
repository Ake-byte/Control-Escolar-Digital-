package com.controldigital.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.controldigital.app.auth.handler.LoginSuccessHandler;
import com.controldigital.app.service.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Autowired
	private BCryptPasswordEncoder passwordEnconder;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/register/**", "/layout/layout/**", "/newPassword/**", "/newPasswordForm/**").permitAll()
		.antMatchers("/uploads/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/index/**").hasAnyRole("ADMIN", "USER2", "USER1")
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.successHandler(successHandler)
			.loginPage("/login")
			.usernameParameter("email")
		.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		
		builder.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEnconder);
	}
}
