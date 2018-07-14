package com.antoniojnavarro.naventory.dao.repositories;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.Ejemplo;
import com.antoniojnavarro.naventory.model.filters.EjemploSearchFilter;

public interface EjemploDao
		extends Dao<Ejemplo, EjemploSearchFilter, Long> {

}
