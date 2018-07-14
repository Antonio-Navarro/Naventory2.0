package com.antoniojnavarro.naventory.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.dao.commons.annotations.TransactionRequiredNew;
import com.antoniojnavarro.naventory.services.api.ServicioEjemploRequiredNewTransaction;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

@Service
public class ServicioEjemploRequiredNewTransactionImpl implements ServicioEjemploRequiredNewTransaction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ServicioEjemploRequiredNewTransactionImpl.class);

	@Override
	@TransactionRequiredNew
	public String operationWithNewTransaction() throws ServicioException {
		logger.info("ServicioEjemploRequiredNewTransactionImpl.operationWithNewTransaction()");
		return this.getClass().getSimpleName();

	}
}