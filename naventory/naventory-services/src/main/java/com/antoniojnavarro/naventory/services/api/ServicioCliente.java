package com.antoniojnavarro.naventory.services.api;

import java.util.List;

import com.antoniojnavarro.naventory.model.entities.Cliente;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.ClienteSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioCliente extends ServicioCrud<Cliente, ClienteSearchFilter, Integer> {

	List<Cliente> findClientesByUsuario(Usuario user) throws ServicioException;

	Object[] findClientesGrafica(String email);

	Long countByUsuario(Usuario usuario);

}
