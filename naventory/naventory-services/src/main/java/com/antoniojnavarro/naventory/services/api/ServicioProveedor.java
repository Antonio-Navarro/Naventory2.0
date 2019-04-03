package com.antoniojnavarro.naventory.services.api;

import java.util.List;

import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Proveedor;
import com.antoniojnavarro.naventory.model.filters.ProveedorSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioProveedor extends ServicioCrud<Proveedor, ProveedorSearchFilter, Integer> {

	List<Proveedor> findProveedoresByEmpresa(Empresa empresa) throws ServicioException;

}
