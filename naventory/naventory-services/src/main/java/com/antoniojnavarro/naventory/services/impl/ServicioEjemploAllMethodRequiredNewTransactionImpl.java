package com.antoniojnavarro.naventory.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.dao.commons.annotations.TransactionRequiredNew;
import com.antoniojnavarro.naventory.services.api.ServicioEjemploAllMethodRequiredNewTransaction;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

@Service
@TransactionRequiredNew
public class ServicioEjemploAllMethodRequiredNewTransactionImpl
		implements ServicioEjemploAllMethodRequiredNewTransaction {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(ServicioEjemploAllMethodRequiredNewTransactionImpl.class);

	@Override
	public String oneOperationWithNewTransaction() throws ServicioException {
		logger.info("ServicioEjemploAllMethodRequiredNewTransactionImpl.oneOperationWithNewTransaction()");
		return this.getClass().getSimpleName();
	}

	@Override
	public String twoOperationWithNewTransaction() throws ServicioException {
		logger.info("ServicioEjemploAllMethodRequiredNewTransactionImpl.twoOperationWithNewTransaction()");
		return this.getClass().getSimpleName();
	}

}
