package com.antoniojnavarro.naventory.services.api;

import java.util.List;

import com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto;
import com.antoniojnavarro.naventory.model.entities.Cliente;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.filters.ClienteSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;

public interface ServicioCliente extends ServicioCrud<Cliente, ClienteSearchFilter, Integer> {

	List<GraficaGenericDto> findClientesGrafica(String empresa);

	Long countByEmpresa(Empresa empresa);

}
