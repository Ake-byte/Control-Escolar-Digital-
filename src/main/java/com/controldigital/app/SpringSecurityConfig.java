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

/**
 * Clase de configuración de seguridad en el sistema.
 * Spring Security gestiona los accesos que tiene cada usuario así como sus roles .
 */
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler successHandler;

    @Autowired
    private BCryptPasswordEncoder passwordEnconder;

    @Autowired
    private JpaUserDetailsService userDetailsService;

    /**
     * Método que indica el nombre del recurso y el nombre del usuario que tiene acceso a este.
     * Los recursos son las vistas en html que retornan los controladores y están ubicadas en;
     * /src/main/resources/templates/
     *
     * @param http
     * @throws Exception
     */

    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/register/**", "/layout/layout/**", "/newPassword/**", "/newPasswordForm/**").permitAll()
                .antMatchers("/index/**").hasAnyRole("ADMIN", "USER2", "USER1")
                .antMatchers("/uploads/**").hasAnyRole("ADMIN", "USER2")
                .antMatchers("/PersonalAutorizado/**").hasAnyRole("ADMIN")
                .antMatchers("/Sistema/**").hasAnyRole("ADMIN", "USER2")
                .antMatchers("/Academica/**").hasAnyRole("USER2")
                .antMatchers("/Expediente/**").hasAnyRole("USER2")
                .antMatchers("/Personal/**").hasAnyRole("USER2")
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

    /**
     * Método que encripta la contraseña del usuario
     *
     * @param builder
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {

        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEnconder);
    }
}
