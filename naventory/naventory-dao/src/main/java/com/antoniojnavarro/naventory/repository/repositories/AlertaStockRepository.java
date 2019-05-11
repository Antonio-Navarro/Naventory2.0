package com.antoniojnavarro.naventory.repository.repositories;

import java.util.List;

import com.antoniojnavarro.naventory.model.entities.AlertaStock;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.filters.AlertaStockSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.api.BaseRepository;

public interface AlertaStockRepository extends BaseRepository<AlertaStock, AlertaStockSearchFilter, Integer> {

	List<AlertaStock> findAlertasByEmpresa(Empresa empresa); 
		AlertaStock findAlertaByEmpresaAndProducto(Empresa empresa, Producto product); 
}
