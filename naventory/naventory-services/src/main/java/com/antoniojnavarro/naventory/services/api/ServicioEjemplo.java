package com.antoniojnavarro.naventory.services.api;

import com.antoniojnavarro.naventory.model.entities.Ejemplo;
import com.antoniojnavarro.naventory.model.filters.EjemploSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;

public interface ServicioEjemplo extends ServicioCrud<Ejemplo, EjemploSearchFilter, Long> {
	
}
