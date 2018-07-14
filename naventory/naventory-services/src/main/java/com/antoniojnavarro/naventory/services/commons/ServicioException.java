package com.antoniojnavarro.naventory.services.commons;

public class ServicioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServicioException() {
		super();
	}

	public ServicioException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServicioException(String message) {
		super(message);
	}
}
