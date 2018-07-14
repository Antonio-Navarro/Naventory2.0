//package com.antoniojnavarro.naventory.dao.repositories;
//
//import org.springframework.data.jpa.repository.Query;
//
//import com.antoniojnavarro.naventory.dao.commons.api.Dao;
//import com.antoniojnavarro.naventory.model.entities.Usuario;
//import com.antoniojnavarro.naventory.model.filters.UsuarioSearchFilter;
//
//public interface UsuarioDao extends Dao<Usuario, UsuarioSearchFilter, Long> {
//
//	@Query("SELECT COUNT(u) FROM Usuario u WHERE u.login = ?1")
//	Long countUsuarioByLogin(String usuario);
//
//	@Query("SELECT u FROM Usuario u JOIN FETCH u.roles WHERE u.login = ?1")
//	Usuario findUsuarioByLogin(String login);
//
//}
