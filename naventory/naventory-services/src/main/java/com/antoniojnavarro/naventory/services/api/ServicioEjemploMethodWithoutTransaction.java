package com.antoniojnavarro.naventory.services.api;

import java.io.Serializable;

import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioEjemploMethodWithoutTransaction extends Serializable {

	String withoutTransaction() throws ServicioException;
	

}
