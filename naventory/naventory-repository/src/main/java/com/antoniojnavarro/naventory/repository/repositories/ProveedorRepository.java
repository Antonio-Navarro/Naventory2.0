package com.antoniojnavarro.naventory.repository.repositories;

import java.util.List;

import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Proveedor;
import com.antoniojnavarro.naventory.model.filters.ProveedorSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.api.BaseRepository;

public interface ProveedorRepository extends BaseRepository<Proveedor, ProveedorSearchFilter, Integer> {

	List<Proveedor> findProveedoresByEmpresa(Empresa empresa); 
	

}
