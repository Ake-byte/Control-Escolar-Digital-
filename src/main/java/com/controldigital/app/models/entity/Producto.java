package com.controldigital.app.models.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "productos")
public class Producto implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long id;
	
	private String nombreProducto;
	
	private String lugarProducto;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inicioPeriodo;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date terminoPeriodo;

	private String paisProducto;
	
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


	public String getLugarProducto() {
		return lugarProducto;
	}

	public void setLugarProducto(String lugarProducto) {
		this.lugarProducto = lugarProducto;
	}

	public Date getInicioPeriodo() {
		return inicioPeriodo;
	}

	public void setInicioPeriodo(Date inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}

	public Date getTerminoPeriodo() {
		return terminoPeriodo;
	}

	public void setTerminoPeriodo(Date terminoPeriodo) {
		this.terminoPeriodo = terminoPeriodo;
	}

	public String getPaisProducto() {
		return paisProducto;
	}

	public void setPaisProducto(String paisProducto) {
		this.paisProducto = paisProducto;
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
