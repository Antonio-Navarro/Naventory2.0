package com.antoniojnavarro.naventory.services.api;

import java.util.List;

import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.entities.Venta;
import com.antoniojnavarro.naventory.model.filters.VentaSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioVenta extends ServicioCrud<Venta, VentaSearchFilter, Integer> {

	List<Venta> findVentasByUsuario(Usuario user) throws ServicioException;

	void validarStock(Venta entity) throws ServicioException;

	void comprobarAlerta(Venta entity) throws ServicioException;

	Venta calcularVenta(Venta entity) throws ServicioException;
}
