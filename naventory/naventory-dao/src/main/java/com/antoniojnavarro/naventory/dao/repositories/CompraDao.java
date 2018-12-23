package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto;
import com.antoniojnavarro.naventory.model.entities.Compra;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.CompraSearchFilter;

public interface CompraDao extends Dao<Compra, CompraSearchFilter, Integer> {

	List<Compra> findComprasByUsuarioOrderByFechaDesc(Usuario user);

	@Query("select new com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto(date_format(c.fecha,'%m-%Y'),  round(sum(c.total),2)) from Compra c where c.usuario.email= ?1 group by date_format(c.fecha,'%m-%Y')")
	List<GraficaGenericDto> getGastosMensualesGrafica(String email);

	Long countByUsuario(Usuario usuario);
}
