package com.antoniojnavarro.naventory.services.api;

import java.util.List;

import com.antoniojnavarro.naventory.model.entities.Compra;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.CompraSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioCompra extends ServicioCrud<Compra, CompraSearchFilter, Integer> {

	List<Compra> findComprasByUsuario(Usuario user) throws ServicioException;

	Compra calcularCompra(Compra entity);

	Compra saveNewOrUpdate(Compra entity, boolean validate, boolean nuevoProducto) throws ServicioException;

	List<Object> getGastosMensualesGrafica(String email);

	Long countByUsuario(Usuario usuario);
}
