package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.AlertaStock;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.AlertaStockSearchFilter;

public interface AlertaStockDao extends Dao<AlertaStock, AlertaStockSearchFilter, Integer> {

	@Query("SELECT c FROM AlertaStock c WHERE c.usuario = ?1")
	List<AlertaStock> findAlertasByUsuario(Usuario user); 
	
	@Query("SELECT c FROM AlertaStock c WHERE c.usuario = ?1 and c.producto= ?2")
	AlertaStock findAlertaByUsuarioAndProducto(Usuario user, Producto product); 
}
