package com.controldigital.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.controldigital.app.models.entity.Producto;

/**
 * Interfaz con operaciones CRUD para objetos del tipo "Producto"
 */
public interface IProductoDAO extends CrudRepository<Producto, Long>{

}
