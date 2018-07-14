package com.antoniojnavarro.naventory.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.dao.commons.annotations.TransactionNever;
import com.antoniojnavarro.naventory.services.api.ServicioEjemploAllMethodRequiredNewTransaction;
import com.antoniojnavarro.naventory.services.api.ServicioEjemploAllMethodsWithoutTransaction;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

@Service
@TransactionNever
public class ServicioEjemploAllMethodsWithoutTransactionImpl implements ServicioEjemploAllMethodsWithoutTransaction {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ServicioEjemploAllMethodsWithoutTransactionImpl.class);

	@Autowired
	private ServicioEjemploAllMethodRequiredNewTransaction srvEjemploAllMethodRequiredNewTransaction;

	@Override
	public String oneMethodWithoutTransaction() throws ServicioException {
		logger.info("ServicioEjemploAllMethodsWithoutTransactionImpl.oneMethodWithoutTransaction()");
		return this.srvEjemploAllMethodRequiredNewTransaction.oneOperationWithNewTransaction();
	}

	@Override
	public String twoMethodWithoutTransaction() throws ServicioException {
		logger.info("ServicioEjemploAllMethodsWithoutTransactionImpl.twoMethodWithoutTransaction()");
		return this.srvEjemploAllMethodRequiredNewTransaction.twoOperationWithNewTransaction();
	}

}
