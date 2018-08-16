package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.Cliente;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.ClienteSearchFilter;

public interface ClienteDao extends Dao<Cliente, ClienteSearchFilter, Integer> {

	@Query("SELECT c FROM Cliente c WHERE c.usuario = ?1")
	List<Cliente> findClientesByUsuario(Usuario user); 
	

}
