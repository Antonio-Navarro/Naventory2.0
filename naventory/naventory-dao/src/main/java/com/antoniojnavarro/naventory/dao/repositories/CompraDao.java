package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto;
import com.antoniojnavarro.naventory.model.entities.Compra;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.filters.CompraSearchFilter;

public interface CompraDao extends Dao<Compra, CompraSearchFilter, Integer> {

	@Query("select new com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto(date_format(c.fecha,'%m-%Y'),  round(sum(c.total),2)) from Compra c where c.empresa.cif= ?1 group by date_format(c.fecha,'%m-%Y')")
	List<GraficaGenericDto> getGastosMensualesGrafica(String cif);

	Long countByEmpresa(Empresa empresa);

	Compra findByEmpresaAndFactura(String cif, String factura);
}
