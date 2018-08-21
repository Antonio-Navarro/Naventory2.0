package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.entities.Venta;
import com.antoniojnavarro.naventory.model.filters.VentaSearchFilter;

public interface VentaDao extends Dao<Venta, VentaSearchFilter, Integer> {

	@Query("SELECT p FROM Venta p WHERE p.usuario = ?1")
	List<Venta> findVentasByUsuario(Usuario user); 
	

}
