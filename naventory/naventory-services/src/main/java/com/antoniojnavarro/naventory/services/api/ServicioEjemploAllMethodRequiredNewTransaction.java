package com.antoniojnavarro.naventory.services.api;

import java.io.Serializable;

import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioEjemploAllMethodRequiredNewTransaction extends Serializable {

	String oneOperationWithNewTransaction() throws ServicioException;
	
	String twoOperationWithNewTransaction() throws ServicioException;

}
