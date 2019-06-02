package com.antoniojnavarro.naventory.repository.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Novedad;
import com.antoniojnavarro.naventory.model.filters.NovedadSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.api.BaseRepository;

public interface NovedadRepository extends BaseRepository<Novedad, NovedadSearchFilter, Integer>{

	@Query("SELECT c FROM Novedad c WHERE c.empresa = ?1 order by c.fecha desc")
	List<Novedad> findNovedadesByEmpresa(Empresa empresa, Pageable p);

	
}
