package com.antoniojnavarro.naventory.services.api;

import java.util.List;

import com.antoniojnavarro.naventory.model.entities.FormaPago;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.FormaPagoSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioFormaPago extends ServicioCrud<FormaPago, FormaPagoSearchFilter, Integer> {

	List<FormaPago> findFormasPagoByUsuario() throws ServicioException;

}
