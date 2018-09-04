package com.antoniojnavarro.naventory.dao.repositories;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.UsuarioSearchFilter;

public interface UsuarioDao extends Dao<Usuario, UsuarioSearchFilter, String> {

	@Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.email = ?1")
	boolean existsUsuarioByEmail(String usuario);

	@Query("SELECT u FROM Usuario u WHERE u.email = ?1")
	Usuario findUsuarioByEmail(String email);

	@Query("SELECT u FROM Usuario u WHERE u.email = ?1 and u.password= ?2")
	Usuario findUsuarioByEmailAndPassword(String email, String pass);
	
	@Query("SELECT year(u.fecha_alta), count(email) FROM Usuario u group by year(u.fecha_alta) order by year(u.fecha_alta) asc")
	Object[] findUsersGrafica();
}
