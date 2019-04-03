package com.antoniojnavarro.naventory.services.api;

import java.util.List;

import com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Venta;
import com.antoniojnavarro.naventory.model.filters.VentaSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioVenta extends ServicioCrud<Venta, VentaSearchFilter, Integer> {

	void validarStock(Venta entity) throws ServicioException;

	Venta calcularVenta(Venta entity) throws ServicioException;

	List<GraficaGenericDto> findFormasPagoGrafica(Empresa empresa);

	List<GraficaGenericDto> getVentasMensualesGrafica(Empresa empresa);

	List<GraficaGenericDto> getIngresosMensualesGrafica(Empresa empresa);

	Long countByEmpresa(Empresa empresa);
}
