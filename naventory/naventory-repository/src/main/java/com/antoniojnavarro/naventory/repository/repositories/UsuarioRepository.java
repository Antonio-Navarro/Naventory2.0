package com.antoniojnavarro.naventory.repository.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.UsuarioSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.api.BaseRepository;

public interface UsuarioRepository extends BaseRepository<Usuario, UsuarioSearchFilter, String> {

	@Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.email = ?1")
	boolean existsUsuarioByEmail(String usuario);

	// @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
	Usuario findUsuarioByEmail(String email);

	List<Usuario> findUsuarioByEmpresa(Empresa empresa);

	// @Query("SELECT u FROM Usuario u WHERE u.email = ?1 and u.password= ?2")
	Usuario findUsuarioByEmailAndPassword(String email, String pass);

	// @Query("SELECT u FROM Usuario u WHERE u.email = ?1 and u.activo = ?2")
	Usuario findUsuarioByEmailAndActivo(String email, String activo);

	@Query("SELECT year(u.fechaAlta), count(email) FROM Usuario u group by year(u.fechaAlta) order by year(u.fechaAlta) asc")
	Object[] findUsersGrafica();
}
