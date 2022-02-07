package com.controldigital.app.models.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entidad que mapea la tabla "personal" en la base de datos
 */
@Entity
@Table(name = "personal")
public class InfoPersonal implements Serializable {

    /**
     * Identificador único del campo personal
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_personal_id")
    private Long id;

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

    /**
     * Género del usuario, pueden ser:
     * - Hombre
     * - Mujer
     * - Otro
     */
    private String genero;

    /**
     * Campo booleano que indica si el usuario habla una lengua indígena
     */
    @Column(name = "lengua_indigena")
    private Boolean lenguaIndigena;

    @Column(name = "nombre_lengua_indigena")
    private String nombreLenguaIndigena;

    /**
     * Campo booleano que indica si el usuario padece de una discapacidad
     */
    @Column(name = "discapacidad")
    private Boolean discapacidad;

    @Column(name = "nombre_discapacidad")
    private String nombreDiscapacidad;

    /**
     * Campo booleano que indica si el usuario padece de una enfermedad permanente
     */
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

    /**
     * FK al campo PK "user_id" de la tabla users
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Usuario users;

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

    public FileStatus getActaStatus() {
        return actaStatus;
    }

    public void setActaStatus(FileStatus actaStatus) {
        this.actaStatus = actaStatus;
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

    public FileStatus getCurpStatus() {
        return curpStatus;
    }

    public void setCurpStatus(FileStatus curpStatus) {
        this.curpStatus = curpStatus;
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

    public FileStatus getFotoStatus() {
        return fotoStatus;
    }

    public void setFotoStatus(FileStatus fotoStatus) {
        this.fotoStatus = fotoStatus;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public FileStatus getPasaporteStatus() {
        return pasaporteStatus;
    }

    public void setPasaporteStatus(FileStatus pasaporteStatus) {
        this.pasaporteStatus = pasaporteStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsers() {
        return users;
    }

    public void setUsers(Usuario users) {
        this.users = users;
    }
}
