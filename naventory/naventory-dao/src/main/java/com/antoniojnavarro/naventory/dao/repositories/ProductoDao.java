package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.ProductoSearchFilter;

public interface ProductoDao extends Dao<Producto, ProductoSearchFilter, String> {

	@Query("SELECT p FROM Producto p WHERE p.usuario = ?1")
	List<Producto> findProductosByUsuario(Usuario user); 
	@Query("SELECT sum(p.precio * p.stock) FROM Producto p where p.usuario = ?1")
	Float getTotalInventario(Usuario user); 
	

}
