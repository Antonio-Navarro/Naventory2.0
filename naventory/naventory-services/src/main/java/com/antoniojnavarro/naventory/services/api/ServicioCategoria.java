package com.antoniojnavarro.naventory.services.api;

import java.util.List;

import com.antoniojnavarro.naventory.model.entities.Categoria;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.filters.CategoriaSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioCategoria extends ServicioCrud<Categoria, CategoriaSearchFilter, Integer> {

	List<Categoria> findCategoriasByEmpresa(Empresa empresa) throws ServicioException;

}
