package com.antoniojnavarro.naventory.services.dto.api;

import com.antoniojnavarro.naventory.model.entities.Ejemplo;
import com.antoniojnavarro.naventory.model.filters.EjemploSearchFilter;
import com.antoniojnavarro.naventory.services.commons.dto.ServicioCrudDto;
import com.antoniojnavarro.naventory.services.dto.EjemploDto;

public interface ServicioEjemploDto extends ServicioCrudDto<EjemploDto, Ejemplo, EjemploSearchFilter, Long> {

}
