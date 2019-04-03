package com.antoniojnavarro.naventory.services.api;

import java.util.List;

import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Novedad;
import com.antoniojnavarro.naventory.model.filters.NovedadSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioNovedad extends ServicioCrud<Novedad, NovedadSearchFilter, Integer> {

	List<Novedad> findNovedadesByEmpresa(Empresa empresa, Integer limit) throws ServicioException;


}
