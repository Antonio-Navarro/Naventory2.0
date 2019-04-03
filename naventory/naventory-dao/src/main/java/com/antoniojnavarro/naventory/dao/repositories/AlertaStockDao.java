package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.AlertaStock;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.filters.AlertaStockSearchFilter;

public interface AlertaStockDao extends Dao<AlertaStock, AlertaStockSearchFilter, Integer> {

	List<AlertaStock> findAlertasByEmpresa(Empresa empresa); 
		AlertaStock findAlertaByEmpresaAndProducto(Empresa empresa, Producto product); 
}
