package com.antoniojnavarro.naventory.repository.repositories;

import java.util.List;

import com.antoniojnavarro.naventory.model.entities.Categoria;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.filters.CategoriaSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.api.BaseRepository;

public interface CategoriaRepository extends BaseRepository<Categoria, CategoriaSearchFilter, Integer> {

	List<Categoria> findCategoriasByEmpresa(Empresa empresa); 

}
