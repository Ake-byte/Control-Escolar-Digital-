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

    @Column(name = "acta_invalid_status")
    private InvalidStatus actaInvalidStatus;

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

    @Column(name = "curp_invalid_status")
    private InvalidStatus curpInvalidStatus;

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

    @Column(name = "foto_invalid_status")
    private InvalidStatus fotoInvalidStatus;

    @Column(name = "pasaporte")
    private String pasaporte;

    @Column(name = "pasaporte_status")
    private FileStatus pasaporteStatus;

    @Column(name = "pasaporte_invalid_status")
    private InvalidStatus pasaporteInvalidStatus;

    @Column(name = "pwd", length = 100)
    private String password;

    private Boolean enabled;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @Column(name = "nombre_licenciatura")
    private String nombreLicenciatura;

    @Column(name = "institucion_licenciatura")
    private String institucionLicenciatura;

    @Column(name = "pais_licenciatura")
    private String paisLicenciatura;

    @Column(name = "promedio_licenciatura")
    private String promedioLicenciatura;

    @Column(name = "calificaciones_licenciatura")
    private String calificacionesLicenciatura;

    @Column(name = "calificaciones_licenciatura_status")
    private FileStatus calificacionesLicenciaturaStatus;

    @Column(name = "calificaciones_licenciatura_invalid_status")
    private InvalidStatus calificacionesLicenciaturaInvalidStatus;

    @Column(name = "diploma_licenciatura")
    private String diplomaLicenciatura;

    @Column(name = "diploma_licenciatura_status")
    private FileStatus diplomaLicenciaturaStatus;

    @Column(name = "diploma_licenciatura_invalid_status")
    private InvalidStatus diplomaLicenciaturaInvalidStatus;

    @Column(name = "cedula_licenciatura")
    private String cedulaLicenciatura;

    @Column(name = "cedula_licenciatura_status")
    private FileStatus cedulaLicenciaturaStatus;

    @Column(name = "cedula_licenciatura_invalid_status")
    private InvalidStatus cedulaLicenciaturaInvalidStatus;

    @Column(name = "acreditacion_ingles")
    private String acreditacionIngles;

    @Column(name = "acreditacion_ingles_status")
    private FileStatus acreditacionInglesStatus;

    @Column(name = "acreditacion_ingles_invalid_status")
    private InvalidStatus acreditacionInglesInvalidStatus;

    @Column(name = "nombre_maestria")
    private String nombreMaestria;

    @Column(name = "institucion_maestria")
    private String institucionMaestria;

    @Column(name = "pais_maestria")
    private String paisMaestria;

    @Column(name = "promedio_maestria")
    private String promedioMaestria;

    @Column(name = "calificaciones_maestria")
    private String calificacionesMaestria;

    @Column(name = "calificaciones_maestria_status")
    private FileStatus calificacionesMaestriaStatus;

    @Column(name = "calificaciones_maestria_invalid_status")
    private InvalidStatus calificacionesMaestriaInvalidStatus;

    @Column(name = "diploma_examen_maestria")
    private String actaExamenMaestria;

    @Column(name = "acta_examen_maestria_status")
    private FileStatus actaExamenMaestriaStatus;

    @Column(name = "acta_examen_maestria_invalid_status")
    private InvalidStatus actaExamenMaestriaInvalidStatus;

    @Column(name = "diploma_maestria")
    private String diplomaMaestria;

    @Column(name = "diploma_maestria_status")
    private FileStatus diplomaMaestriaStatus;

    @Column(name = "diploma_maestria_invalid_status")
    private InvalidStatus diplomaMaestriaInvalidStatus;

    @Column(name = "cedula_maestria")
    private String cedulaMaestria;

    @Column(name = "cedula_maestria_status")
    private FileStatus cedulaMaestriaStatus;

    @Column(name = "cedula_maestria_invalid_status")
    private InvalidStatus cedulaMaestriaInvalidStatus;

    @Column(name = "grado")
    private String grado;

    @Column(name = "numero_registro")
    private String numeroRegistro;

    private String semestre;

    @Column(name = "estatus_escolar")
    private String estatusEscolar;

    @Column(name = "beca_conacyt")
    private Boolean becaConacyt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Role roles;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Producto> productos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<SIP> sips;

    public Usuario() {
        productos = new ArrayList<Producto>();
        sips = new ArrayList<SIP>();
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

    public String getNombreLicenciatura() {
        return nombreLicenciatura;
    }

    public void setNombreLicenciatura(String nombreLicenciatura) {
        this.nombreLicenciatura = nombreLicenciatura;
    }

    public String getInstitucionLicenciatura() {
        return institucionLicenciatura;
    }

    public void setInstitucionLicenciatura(String institucionLicenciatura) {
        this.institucionLicenciatura = institucionLicenciatura;
    }

    public String getPaisLicenciatura() {
        return paisLicenciatura;
    }

    public void setPaisLicenciatura(String paisLicenciatura) {
        this.paisLicenciatura = paisLicenciatura;
    }

    public String getPromedioLicenciatura() {
        return promedioLicenciatura;
    }

    public void setPromedioLicenciatura(String promedioLicenciatura) {
        this.promedioLicenciatura = promedioLicenciatura;
    }

    public String getCalificacionesLicenciatura() {
        return calificacionesLicenciatura;
    }

    public void setCalificacionesLicenciatura(String calificacionesLicenciatura) {
        this.calificacionesLicenciatura = calificacionesLicenciatura;
    }

    public FileStatus getCalificacionesLicenciaturaStatus() {
        return calificacionesLicenciaturaStatus;
    }

    public void setCalificacionesLicenciaturaStatus(FileStatus calificacionesLicenciaturaStatus) {
        this.calificacionesLicenciaturaStatus = calificacionesLicenciaturaStatus;
    }

    public String getDiplomaLicenciatura() {
        return diplomaLicenciatura;
    }

    public void setDiplomaLicenciatura(String diplomaLicenciatura) {
        this.diplomaLicenciatura = diplomaLicenciatura;
    }

    public FileStatus getDiplomaLicenciaturaStatus() {
        return diplomaLicenciaturaStatus;
    }

    public void setDiplomaLicenciaturaStatus(FileStatus diplomaLicenciaturaStatus) {
        this.diplomaLicenciaturaStatus = diplomaLicenciaturaStatus;
    }

    public String getCedulaLicenciatura() {
        return cedulaLicenciatura;
    }

    public void setCedulaLicenciatura(String cedulaLicenciatura) {
        this.cedulaLicenciatura = cedulaLicenciatura;
    }

    public FileStatus getCedulaLicenciaturaStatus() {
        return cedulaLicenciaturaStatus;
    }

    public void setCedulaLicenciaturaStatus(FileStatus cedulaLicenciaturaStatus) {
        this.cedulaLicenciaturaStatus = cedulaLicenciaturaStatus;
    }

    public String getAcreditacionIngles() {
        return acreditacionIngles;
    }

    public void setAcreditacionIngles(String acreditacionIngles) {
        this.acreditacionIngles = acreditacionIngles;
    }

    public FileStatus getAcreditacionInglesStatus() {
        return acreditacionInglesStatus;
    }

    public void setAcreditacionInglesStatus(FileStatus acreditacionInglesStatus) {
        this.acreditacionInglesStatus = acreditacionInglesStatus;
    }

    public String getNombreMaestria() {
        return nombreMaestria;
    }

    public void setNombreMaestria(String nombreMaestria) {
        this.nombreMaestria = nombreMaestria;
    }

    public String getInstitucionMaestria() {
        return institucionMaestria;
    }

    public void setInstitucionMaestria(String institucionMaestria) {
        this.institucionMaestria = institucionMaestria;
    }

    public String getPaisMaestria() {
        return paisMaestria;
    }

    public void setPaisMaestria(String paisMaestria) {
        this.paisMaestria = paisMaestria;
    }

    public String getPromedioMaestria() {
        return promedioMaestria;
    }

    public void setPromedioMaestria(String promedioMaestria) {
        this.promedioMaestria = promedioMaestria;
    }

    public String getCalificacionesMaestria() {
        return calificacionesMaestria;
    }

    public void setCalificacionesMaestria(String calificacionesMaestria) {
        this.calificacionesMaestria = calificacionesMaestria;
    }

    public FileStatus getCalificacionesMaestriaStatus() {
        return calificacionesMaestriaStatus;
    }

    public void setCalificacionesMaestriaStatus(FileStatus calificacionesMaestriaStatus) {
        this.calificacionesMaestriaStatus = calificacionesMaestriaStatus;
    }

    public String getDiplomaMaestria() {
        return diplomaMaestria;
    }

    public void setDiplomaMaestria(String diplomaMaestria) {
        this.diplomaMaestria = diplomaMaestria;
    }

    public FileStatus getDiplomaMaestriaStatus() {
        return diplomaMaestriaStatus;
    }

    public void setDiplomaMaestriaStatus(FileStatus diplomaMaestriaStatus) {
        this.diplomaMaestriaStatus = diplomaMaestriaStatus;
    }

    public String getCedulaMaestria() {
        return cedulaMaestria;
    }

    public void setCedulaMaestria(String cedulaMaestria) {
        this.cedulaMaestria = cedulaMaestria;
    }

    public FileStatus getCedulaMaestriaStatus() {
        return cedulaMaestriaStatus;
    }

    public void setCedulaMaestriaStatus(FileStatus cedulaMaestriaStatus) {
        this.cedulaMaestriaStatus = cedulaMaestriaStatus;
    }

    public String getActaExamenMaestria() {
        return actaExamenMaestria;
    }

    public void setActaExamenMaestria(String actaExamenMaestria) {
        this.actaExamenMaestria = actaExamenMaestria;
    }

    public FileStatus getActaExamenMaestriaStatus() {
        return actaExamenMaestriaStatus;
    }

    public void setActaExamenMaestriaStatus(FileStatus actaExamenMaestriaStatus) {
        this.actaExamenMaestriaStatus = actaExamenMaestriaStatus;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public InvalidStatus getActaInvalidStatus() {
        return actaInvalidStatus;
    }

    public void setActaInvalidStatus(InvalidStatus actaInvalidStatus) {
        this.actaInvalidStatus = actaInvalidStatus;
    }

    public InvalidStatus getCurpInvalidStatus() {
        return curpInvalidStatus;
    }

    public void setCurpInvalidStatus(InvalidStatus curpInvalidStatus) {
        this.curpInvalidStatus = curpInvalidStatus;
    }

    public InvalidStatus getFotoInvalidStatus() {
        return fotoInvalidStatus;
    }

    public void setFotoInvalidStatus(InvalidStatus fotoInvalidStatus) {
        this.fotoInvalidStatus = fotoInvalidStatus;
    }

    public InvalidStatus getPasaporteInvalidStatus() {
        return pasaporteInvalidStatus;
    }

    public void setPasaporteInvalidStatus(InvalidStatus pasaporteInvalidStatus) {
        this.pasaporteInvalidStatus = pasaporteInvalidStatus;
    }

    public InvalidStatus getCalificacionesLicenciaturaInvalidStatus() {
        return calificacionesLicenciaturaInvalidStatus;
    }

    public void setCalificacionesLicenciaturaInvalidStatus(InvalidStatus calificacionesLicenciaturaInvalidStatus) {
        this.calificacionesLicenciaturaInvalidStatus = calificacionesLicenciaturaInvalidStatus;
    }

    public InvalidStatus getDiplomaLicenciaturaInvalidStatus() {
        return diplomaLicenciaturaInvalidStatus;
    }

    public void setDiplomaLicenciaturaInvalidStatus(InvalidStatus diplomaLicenciaturaInvalidStatus) {
        this.diplomaLicenciaturaInvalidStatus = diplomaLicenciaturaInvalidStatus;
    }

    public InvalidStatus getCedulaLicenciaturaInvalidStatus() {
        return cedulaLicenciaturaInvalidStatus;
    }

    public void setCedulaLicenciaturaInvalidStatus(InvalidStatus cedulaLicenciaturaInvalidStatus) {
        this.cedulaLicenciaturaInvalidStatus = cedulaLicenciaturaInvalidStatus;
    }

    public InvalidStatus getAcreditacionInglesInvalidStatus() {
        return acreditacionInglesInvalidStatus;
    }

    public void setAcreditacionInglesInvalidStatus(InvalidStatus acreditacionInglesInvalidStatus) {
        this.acreditacionInglesInvalidStatus = acreditacionInglesInvalidStatus;
    }

    public InvalidStatus getCalificacionesMaestriaInvalidStatus() {
        return calificacionesMaestriaInvalidStatus;
    }

    public void setCalificacionesMaestriaInvalidStatus(InvalidStatus calificacionesMaestriaInvalidStatus) {
        this.calificacionesMaestriaInvalidStatus = calificacionesMaestriaInvalidStatus;
    }

    public InvalidStatus getActaExamenMaestriaInvalidStatus() {
        return actaExamenMaestriaInvalidStatus;
    }

    public void setActaExamenMaestriaInvalidStatus(InvalidStatus actaExamenMaestriaInvalidStatus) {
        this.actaExamenMaestriaInvalidStatus = actaExamenMaestriaInvalidStatus;
    }

    public InvalidStatus getDiplomaMaestriaInvalidStatus() {
        return diplomaMaestriaInvalidStatus;
    }

    public void setDiplomaMaestriaInvalidStatus(InvalidStatus diplomaMaestriaInvalidStatus) {
        this.diplomaMaestriaInvalidStatus = diplomaMaestriaInvalidStatus;
    }

    public InvalidStatus getCedulaMaestriaInvalidStatus() {
        return cedulaMaestriaInvalidStatus;
    }

    public void setCedulaMaestriaInvalidStatus(InvalidStatus cedulaMaestriaInvalidStatus) {
        this.cedulaMaestriaInvalidStatus = cedulaMaestriaInvalidStatus;
    }

    public String getEstatusEscolar() {
        return estatusEscolar;
    }

    public void setEstatusEscolar(String estatusEscolar) {
        this.estatusEscolar = estatusEscolar;
    }

    public Boolean getBecaConacyt() {
        return becaConacyt;
    }

    public void setBecaConacyt(Boolean becaConacyt) {
        this.becaConacyt = becaConacyt;
    }
}
