package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.Novedad;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.NovedadSearchFilter;

public interface NovedadDao extends Dao<Novedad, NovedadSearchFilter, Integer> {

	@Query("SELECT c FROM Novedad c WHERE c.usuario = ?1 order by c.fecha desc")
	List<Novedad> findNovedadesByUsuario(Usuario user);

}