package com.controldigital.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sips")
public class SIP implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sip_id")
    private Long id;

    private String nombreSip;

    private String archivoSip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
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

    public String getNombreSip() {
        return nombreSip;
    }

    public void setNombreSip(String nombreSip) {
        this.nombreSip = nombreSip;
    }

    public String getArchivoSip() {
        return archivoSip;
    }

    public void setArchivoSip(String archivoSip) {
        this.archivoSip = archivoSip;
    }
}
