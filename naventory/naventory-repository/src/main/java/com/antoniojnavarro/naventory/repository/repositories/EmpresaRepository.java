package com.antoniojnavarro.naventory.repository.repositories;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.filters.EmpresaSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.api.BaseRepository;

public interface EmpresaRepository extends BaseRepository<Empresa, EmpresaSearchFilter, String> {

	@Query("SELECT COUNT(u) > 0 FROM Empresa u WHERE u.cif = ?1")
	boolean existsEmpresaByCif(String empresa);

}
