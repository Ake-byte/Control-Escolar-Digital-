package com.controldigital.app.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entidad que mapea la tabla "users" en la base de datos
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    /**
     * Nombre de usuario
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Apellido paterno del usuario
     */
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    /**
     * Apellido materno del usuario
     */
    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    /**
     * Correo electrónico del usuario.
     * Solo puede existir una cuenta de correo electrónico por usuario.
     * No puede haber dos usuarios con la misma dirección de correo.
     */
    @Column(name = "email", unique = true)
    private String email;

    /**
     * Contraseña o clave de acceso del usuario al sistema.
     * Las contraseñas tienen una longitud máxima de 100 carectéres.
     * Este tamañp es por el cifrado.
     */
    @Column(name = "pwd", length = 100)
    private String password;

    /**
     * Campo booleano que indica si el usuario puede acceder al sistema o no.
     * Por cada registro este valor de será "true",
     * cuando personal autorizado cambie el permiso de un usario a "Usuario Deshabilitado"
     * o cuando el usuario ya haya egresado, el valor cambiará a "false".
     */
    private Boolean enabled;

    /**
     * Cuando el usuario olvida su contraseña, se crea un token único que corresponda a la petición
     * solicitada por el usuario, esto con la finalidad de que no cualquiera cambie la contraseña.
     */
    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    /**
     * Relación uno a uno con la tabla "roles".
     * Cada usuario solo debe tener un permiso en el sistema.
     */
    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Role roles;

    /**
     * Relación uno a muchos con la tabla "productos".
     * Un usuario tiene muchos productos.
     */
    @OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
    //@JoinColumn(name = "user_id")
    private List<Producto> productos;

    /**
     * Relación uno a muchos con la tabla "sips".
     * Un usuario tiene muchos productos.
     */
    @OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
    //@JoinColumn(name = "user_id")
    private List<SIP> sips;

    /**
     * Relación uno a uno con la tabla "academica".
     */
    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private InfoAcademica infoAcademica;

    /**
     * Relación uno a uno con la tabla "personal".
     */
    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private InfoPersonal infoPersonal;

    /**
     * Relación uno a uno con la tabla "expediente".
     * Cada usuario solo debe tener un permiso en el sistema.
     */
    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Expediente expediente;

    public InfoPersonal getInfoPersonal() {
        return infoPersonal;
    }

    public void setInfoPersonal(InfoPersonal infoPersonal) {
        this.infoPersonal = infoPersonal;
    }

    public InfoAcademica getInfoAcademica() {
        return infoAcademica;
    }

    public void setInfoAcademica(InfoAcademica infoAcademica) {
        this.infoAcademica = infoAcademica;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public Usuario() {
        productos = new ArrayList<Producto>();
        sips = new ArrayList<SIP>();
        //expediente = new Expediente();
        //infoPersonal = new InfoPersonal();
        //infoAcademica = new InfoAcademica();
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void addProducto(Producto producto) {
        productos.add(producto);
    }

    public List<SIP> getSips() {
        return sips;
    }

    public void setSips(List<SIP> sips) {
        this.sips = sips;
    }

    public void addSip(SIP sip) {
        sips.add(sip);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }
}
