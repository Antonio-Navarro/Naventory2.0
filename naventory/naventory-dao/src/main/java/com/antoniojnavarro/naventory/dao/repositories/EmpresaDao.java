package com.antoniojnavarro.naventory.dao.repositories;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.filters.EmpresaSearchFilter;

public interface EmpresaDao extends Dao<Empresa, EmpresaSearchFilter, String> {

	@Query("SELECT COUNT(u) > 0 FROM Empresa u WHERE u.cif = ?1")
	boolean existsEmpresaByCif(String empresa);

}
