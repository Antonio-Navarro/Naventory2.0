package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.Categoria;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.filters.CategoriaSearchFilter;

public interface CategoriaDao extends Dao<Categoria, CategoriaSearchFilter, Integer> {

	List<Categoria> findCategoriasByEmpresa(Empresa empresa); 

}
