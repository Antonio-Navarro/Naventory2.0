package com.antoniojnavarro.naventory.services.api;

import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.filters.EmpresaSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioEmpresa extends ServicioCrud<Empresa, EmpresaSearchFilter, String> {

	boolean existsEmpresaByCif(String cif) throws ServicioException;

	void validateCif(String cif) throws ServicioException;

	void validateAndCifOpcional(Empresa entity, boolean validarCif) throws ServicioException;
}
