package com.antoniojnavarro.naventory.services.api;

import java.util.List;

import com.antoniojnavarro.naventory.model.entities.AlertaStock;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.filters.AlertaStockSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioAlertaStock extends ServicioCrud<AlertaStock, AlertaStockSearchFilter, Integer> {

	List<AlertaStock> findAlertasByEmpresa(Empresa empresa) throws ServicioException;

	String comprobarAlerta(Producto entity) throws ServicioException;

	AlertaStock findAlertaByEmpresaAndProducto(Empresa empresa, Producto product) throws ServicioException;

	void comprobarAlertaRecepcionProducto(Producto entity) throws ServicioException;

}
