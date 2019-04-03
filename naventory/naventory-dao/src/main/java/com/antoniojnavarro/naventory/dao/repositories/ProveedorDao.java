package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Proveedor;
import com.antoniojnavarro.naventory.model.filters.ProveedorSearchFilter;

public interface ProveedorDao extends Dao<Proveedor, ProveedorSearchFilter, Integer> {

	List<Proveedor> findProveedoresByEmpresa(Empresa empresa); 
	

}
