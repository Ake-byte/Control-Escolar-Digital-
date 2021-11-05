package com.controldigital.app.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido_paterno")
	private String apellidoPaterno;

	@Column(name = "apellido_materno")
	private String apellidoMaterno;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;
	
	@Column(name = "acta_nacimiento")
	private String actaNacimiento;

	@Column(name = "acta_status")
	private FileStatus actaStatus;

	@Column(name = "pais_nacimiento")
	private String paisNacimiento;

	@Column(name = "estado_nacimiento")
	private String estadoNacimiento;

	@Column(name = "clave_CURP")
	private String claveCURP;
	
	@Column(name = "cedula_CURP")
	private String cedulaCURP;

	@Column(name = "curp_status")
	private FileStatus curpStatus;

	@Column(name = "telefono_celular")
	private String telefonoCelular;

	@Column(name = "direccion_CDMX")
	private String direccionCDMX;

	private String genero;

	@Column(name = "lengua_indigena")
	private Boolean lenguaIndigena;

	@Column(name = "nombre_lengua_indigena")
	private String nombreLenguaIndigena;

	@Column(name = "discapacidad")
	private Boolean discapacidad;

	@Column(name = "nombre_discapacidad")
	private String nombreDiscapacidad;

	@Column(name = "enfermedad_permanente")
	private Boolean enfermedadPermanente;

	@Column(name = "nombre_enfermedad_permanente")
	private String nombreEnfermedadPermanente;

	@Column(name = "foto_actual")
	private String fotoActual;

	@Column(name = "foto_status")
	private FileStatus fotoStatus;

	@Column(name = "pasaporte")
	private String pasaporte;

	@Column(name = "pasaporte_status")
	private FileStatus pasaporteStatus;

	@Column(name = "pwd", length = 100)
	private String password;

	private Boolean enabled;

	@Column(name = "reset_password_token")
	private String resetPasswordToken;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Role roles;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
	private List<Producto> productos;

	public Usuario() {
		productos = new ArrayList<Producto>();
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getActaNacimiento() {
		return actaNacimiento;
	}

	public void setActaNacimiento(String actaNacimiento) {
		this.actaNacimiento = actaNacimiento;
	}

	public String getPaisNacimiento() {
		return paisNacimiento;
	}

	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}

	public String getEstadoNacimiento() {
		return estadoNacimiento;
	}

	public void setEstadoNacimiento(String estadoNacimiento) {
		this.estadoNacimiento = estadoNacimiento;
	}

	public String getClaveCURP() {
		return claveCURP;
	}

	public void setClaveCURP(String claveCURP) {
		this.claveCURP = claveCURP;
	}

	public String getCedulaCURP() {
		return cedulaCURP;
	}

	public void setCedulaCURP(String cedulaCURP) {
		this.cedulaCURP = cedulaCURP;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getDireccionCDMX() {
		return direccionCDMX;
	}

	public void setDireccionCDMX(String direccionCDMX) {
		this.direccionCDMX = direccionCDMX;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Boolean getLenguaIndigena() {
		return lenguaIndigena;
	}

	public void setLenguaIndigena(Boolean lenguaIndigena) {
		this.lenguaIndigena = lenguaIndigena;
	}

	public String getNombreLenguaIndigena() {
		return nombreLenguaIndigena;
	}

	public void setNombreLenguaIndigena(String nombreLenguaIndigena) {
		this.nombreLenguaIndigena = nombreLenguaIndigena;
	}

	public Boolean getDiscapacidad() {
		return discapacidad;
	}

	public void setDiscapacidad(Boolean discapacidad) {
		this.discapacidad = discapacidad;
	}

	public String getNombreDiscapacidad() {
		return nombreDiscapacidad;
	}

	public void setNombreDiscapacidad(String nombreDiscapacidad) {
		this.nombreDiscapacidad = nombreDiscapacidad;
	}

	public Boolean getEnfermedadPermanente() {
		return enfermedadPermanente;
	}

	public void setEnfermedadPermanente(Boolean enfermedadPermanente) {
		this.enfermedadPermanente = enfermedadPermanente;
	}

	public String getNombreEnfermedadPermanente() {
		return nombreEnfermedadPermanente;
	}

	public void setNombreEnfermedadPermanente(String nombreEnfermedadPermanente) {
		this.nombreEnfermedadPermanente = nombreEnfermedadPermanente;
	}

	public String getFotoActual() {
		return fotoActual;
	}

	public void setFotoActual(String fotoActual) {
		this.fotoActual = fotoActual;
	}

	public String getPasaporte() {
		return pasaporte;
	}

	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
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

	public FileStatus getActaStatus() {
		return actaStatus;
	}

	public void setActaStatus(FileStatus actaStatus) {
		this.actaStatus = actaStatus;
	}

	public FileStatus getCurpStatus() {
		return curpStatus;
	}

	public void setCurpStatus(FileStatus curpStatus) {
		this.curpStatus = curpStatus;
	}

	public FileStatus getFotoStatus() {
		return fotoStatus;
	}

	public void setFotoStatus(FileStatus fotoStatus) {
		this.fotoStatus = fotoStatus;
	}

	public FileStatus getPasaporteStatus() {
		return pasaporteStatus;
	}

	public void setPasaporteStatus(FileStatus pasaporteStatus) {
		this.pasaporteStatus = pasaporteStatus;
	}
}
