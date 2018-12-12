package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.Cliente;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.ClienteSearchFilter;

public interface ClienteDao extends Dao<Cliente, ClienteSearchFilter, Integer> {

	List<Cliente> findClientesByUsuario(Usuario user);

	@Query("select date_format(c.fecha_alta,'%d/%m/%Y'), count(*) from Cliente c where c.usuario.email = ?1 group by date_format(c.fecha_alta,'%d/%m/%Y')")
	Object[] findClientesGrafica(String email);

	Long countByUsuario(Usuario usuario);
}
