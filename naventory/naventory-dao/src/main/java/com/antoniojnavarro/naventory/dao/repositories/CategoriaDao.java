package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.Categoria;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.CategoriaSearchFilter;

public interface CategoriaDao extends Dao<Categoria, CategoriaSearchFilter, Integer> {

	@Query("SELECT c FROM Categoria c WHERE c.usuario = ?1")
	List<Categoria> findCategoriasByUsuario(Usuario user);

}
