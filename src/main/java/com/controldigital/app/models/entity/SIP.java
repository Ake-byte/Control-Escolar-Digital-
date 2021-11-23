package com.controldigital.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sips")
public class SIP implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String numeroSip;

    private String archivoSip;

    @ManyToOne(optional=false)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private Usuario users;

    public Usuario getUsers() {
        return users;
    }

    public void setUsers(Usuario users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroSip() {
        return numeroSip;
    }

    public void setNumeroSip(String numeroSip) {
        this.numeroSip = numeroSip;
    }

    public String getArchivoSip() {
        return archivoSip;
    }

    public void setArchivoSip(String archivoSip) {
        this.archivoSip = archivoSip;
    }
}
