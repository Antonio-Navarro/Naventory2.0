package com.antoniojnavarro.naventory.services.commons;

import java.io.Serializable;

public interface ServicioValidacion extends Serializable {

	void isNull(String fieldLabel, Object valueField) throws ServicioException;
	void existUser(String id);

}
