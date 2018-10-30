package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.FormaPago;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.FormaPagoSearchFilter;

public interface FormaPagoDao extends Dao<FormaPago, FormaPagoSearchFilter, Integer> {

	@Query("SELECT c FROM FormaPago c")
	List<FormaPago> findFormasPagoByUsuario(); 
	

}
