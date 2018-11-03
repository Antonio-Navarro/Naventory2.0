package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.Compra;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.CompraSearchFilter;

public interface CompraDao extends Dao<Compra, CompraSearchFilter, Integer> {

	@Query("SELECT p FROM Compra p WHERE p.usuario = ?1 order by p.fecha desc")
	List<Compra> findComprasByUsuario(Usuario user);

	@Query("select date_format(c.fecha,'%m-%Y'),  round(sum(c.total),2) from Compra c where c.usuario.email= ?1 group by date_format(c.fecha,'%m-%Y')")
	List<Object> getGastosMensualesGrafica(String email);
}
