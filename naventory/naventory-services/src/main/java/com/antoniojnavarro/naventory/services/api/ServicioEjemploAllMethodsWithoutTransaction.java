package com.antoniojnavarro.naventory.services.api;

import java.io.Serializable;

import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioEjemploAllMethodsWithoutTransaction extends Serializable {

	String oneMethodWithoutTransaction() throws ServicioException;
	
	String twoMethodWithoutTransaction() throws ServicioException;
	
}
