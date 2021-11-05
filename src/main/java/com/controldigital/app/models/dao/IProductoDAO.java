package com.controldigital.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.controldigital.app.models.entity.Producto;

public interface IProductoDAO extends CrudRepository<Producto, Long>{

}
