package com.controldigital.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long id;
	
	private String nombreProducto;
	
	private String descripcionProducto;
	
	private String archivoProducto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
    private Usuario users;

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombreProducto() {
		return nombreProducto;
	}



	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}



	public String getDescripcionProducto() {
		return descripcionProducto;
	}



	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}



	public String getArchivoProducto() {
		return archivoProducto;
	}



	public void setArchivoProducto(String archivoProducto) {
		this.archivoProducto = archivoProducto;
	}



	public Usuario getUsers() {
		return users;
	}



	public void setUsers(Usuario users) {
		this.users = users;
	}



	private static final long serialVersionUID = 1L;

}
