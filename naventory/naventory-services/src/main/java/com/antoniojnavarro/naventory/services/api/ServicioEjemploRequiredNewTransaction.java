package com.antoniojnavarro.naventory.services.api;

import java.io.Serializable;

import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioEjemploRequiredNewTransaction extends Serializable {

	String operationWithNewTransaction() throws ServicioException;

}
