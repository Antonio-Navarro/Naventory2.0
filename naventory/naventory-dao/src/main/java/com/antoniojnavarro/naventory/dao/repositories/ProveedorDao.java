package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.Proveedor;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.ProveedorSearchFilter;

public interface ProveedorDao extends Dao<Proveedor, ProveedorSearchFilter, Integer> {

	@Query("SELECT p FROM Proveedor p WHERE p.usuario = ?1")
	List<Proveedor> findProveedoresByUsuario(Usuario user); 
	

}
