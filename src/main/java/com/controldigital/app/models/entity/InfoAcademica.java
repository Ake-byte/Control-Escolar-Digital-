package com.controldigital.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "academica")
public class InfoAcademica implements Serializable {

    @Id
    @Column(name = "user_academica_id")
    private Long id;

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

    @Column(name = "diploma_licenciatura")
    private String diplomaLicenciatura;

    @Column(name = "diploma_licenciatura_status")
    private FileStatus diplomaLicenciaturaStatus;

    @Column(name = "cedula_licenciatura")
    private String cedulaLicenciatura;

    @Column(name = "cedula_licenciatura_status")
    private FileStatus cedulaLicenciaturaStatus;

    @Column(name = "acreditacion_ingles")
    private String acreditacionIngles;

    @Column(name = "acreditacion_ingles_status")
    private FileStatus acreditacionInglesStatus;

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

    @Column(name = "diploma_examen_maestria")
    private String actaExamenMaestria;

    @Column(name = "acta_examen_maestria_status")
    private FileStatus actaExamenMaestriaStatus;

    @Column(name = "diploma_maestria")
    private String diplomaMaestria;

    @Column(name = "diploma_maestria_status")
    private FileStatus diplomaMaestriaStatus;

    @Column(name = "cedula_maestria")
    private String cedulaMaestria;

    @Column(name = "cedula_maestria_status")
    private FileStatus cedulaMaestriaStatus;

    @OneToOne(mappedBy = "infoAcademica", fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private Usuario users;

    public Usuario getUsuario() {
        return users;
    }

    public void setUsuario(Usuario usuario) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
