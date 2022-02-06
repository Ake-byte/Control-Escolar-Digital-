package com.controldigital.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entidad que mapea la tabla "expediente" en la base de datos
 */
@Entity
@Table(name = "expediente")
public class Expediente implements Serializable {

    @Column(name = "num_semestre")
    private int numSemestre = 0;

    /**
     * Identificador único del expediente
     */
    @Id
    @Column(name = "user_expediente_id")
    private Long id;

    /**
     * Grado de estudios del alumno, ya sea:
     * - Maestría
     * - Doctorado
     */
    @Column(name = "grado")
    private String grado;

    /**
     * Número de registro de los alumnos con las estructura:
     * A/B Año de ingreso y serial de ingreso.
     *
     * Ejemplo:
     * A21 _ _ _ _
     */
    @Column(name = "numero_registro")
    private String numeroRegistro;

    /**
     * Semestre actual en el que está inscrito el alumno
     */
    private String semestre;

    /**
     * Estatus del alumno, ya sea:
     * - Inscrito
     * - Egresado
     */
    @Column(name = "estatus_escolar")
    private String estatusEscolar;

    /**
     * Campo booleano que indica si el alumno cuenta on beca Conacyt o no
     */
    @Column(name = "beca_conacyt")
    private Boolean becaConacyt;

    private String cvuConacyt;

    /**
     * Asesor de tesis 1 del alumno
     */
    private String asesor1;

    /**
     * Asesor de tesis 1 del alumno
     */
    private String asesor2;

    /**
     * Título de la tesis del alumno
     */
    private String tituloTesis;

    /**
     * Comité tutorial 1 del alumno
     */
    private String comiteTutorial1;

    /**
     * Comité tutorial 2 del alumno
     */
    private String comiteTutorial2;

    /**
     * Comité tutorial 3 del alumno
     */
    private String comiteTutorial3;

    /**
     * Comité tutorial 4 del alumno
     */
    private String comiteTutorial4;

    /**
     * FK al campo PK "user_id" de la tabla users
     */
    @OneToOne(mappedBy = "expediente", fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private Usuario users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Usuario getUsers() {
        return users;
    }

    public void setUsers(Usuario users) {
        this.users = users;
    }

    public String getCvuConacyt() {
        return cvuConacyt;
    }

    public void setCvuConacyt(String cvuConacyt) {
        this.cvuConacyt = cvuConacyt;
    }

    public String getAsesor1() {
        return asesor1;
    }

    public void setAsesor1(String asesor1) {
        this.asesor1 = asesor1;
    }

    public String getAsesor2() {
        return asesor2;
    }

    public void setAsesor2(String asesor2) {
        this.asesor2 = asesor2;
    }

    public String getTituloTesis() {
        return tituloTesis;
    }

    public void setTituloTesis(String tituloTesis) {
        this.tituloTesis = tituloTesis;
    }

    public String getComiteTutorial1() {
        return comiteTutorial1;
    }

    public void setComiteTutorial1(String comiteTutorial1) {
        this.comiteTutorial1 = comiteTutorial1;
    }

    public String getComiteTutorial2() {
        return comiteTutorial2;
    }

    public void setComiteTutorial2(String comiteTutorial2) {
        this.comiteTutorial2 = comiteTutorial2;
    }

    public String getComiteTutorial3() {
        return comiteTutorial3;
    }

    public void setComiteTutorial3(String comiteTutorial3) {
        this.comiteTutorial3 = comiteTutorial3;
    }

    public String getComiteTutorial4() {
        return comiteTutorial4;
    }

    public void setComiteTutorial4(String comiteTutorial4) {
        this.comiteTutorial4 = comiteTutorial4;
    }

    public int getNumSemestre() {
        return numSemestre;
    }

    public void setNumSemestre(int numSemestre) {
        this.numSemestre = numSemestre;
    }
}
