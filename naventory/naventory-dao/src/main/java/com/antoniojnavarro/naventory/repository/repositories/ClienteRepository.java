package com.antoniojnavarro.naventory.repository.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto;
import com.antoniojnavarro.naventory.model.entities.Cliente;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.filters.ClienteSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.api.BaseRepository;

public interface ClienteRepository extends BaseRepository<Cliente, ClienteSearchFilter, Integer> {

	@Query("select new com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto(date_format(c.fecha_alta,'%d/%m/%Y'), count(*)) from Cliente c where c.empresa.cif = ?1 group by date_format(c.fecha_alta,'%d/%m/%Y')")
	List<GraficaGenericDto> findClientesGrafica(String cif);

	Long countByEmpresa(Empresa empresa);
}
