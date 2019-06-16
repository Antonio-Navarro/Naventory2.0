package com.antoniojnavarro.naventory.repository.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.model.entities.FormaPago;
import com.antoniojnavarro.naventory.model.filters.FormaPagoSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.api.BaseRepository;

public interface FormaPagoRepository extends BaseRepository<FormaPago, FormaPagoSearchFilter, Integer> {

	@Query("SELECT c FROM FormaPago c")
	List<FormaPago> findFormasPagoByUsuario(); 
	

}
