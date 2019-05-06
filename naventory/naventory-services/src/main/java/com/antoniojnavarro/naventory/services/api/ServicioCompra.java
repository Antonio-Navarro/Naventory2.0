package com.antoniojnavarro.naventory.services.api;

import java.util.List;

import com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto;
import com.antoniojnavarro.naventory.model.entities.Compra;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.filters.CompraSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioCompra extends ServicioCrud<Compra, CompraSearchFilter, Integer> {

	Compra calcularCompra(Compra entity);

	Compra saveNewOrUpdate(Compra entity, boolean validate, boolean nuevoProducto) throws ServicioException;

	List<GraficaGenericDto> getGastosMensualesGrafica(Empresa empresa, int numMeses);

	Long countByEmpresa(Empresa cif);
}
