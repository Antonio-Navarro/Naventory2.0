package com.antoniojnavarro.naventory.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.dao.commons.annotations.TransactionNever;
import com.antoniojnavarro.naventory.services.api.ServicioEjemploMethodWithoutTransaction;
import com.antoniojnavarro.naventory.services.api.ServicioEjemploRequiredNewTransaction;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

@Service
public class ServicioEjemploMethodWithoutTransactionImpl implements ServicioEjemploMethodWithoutTransaction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ServicioEjemploMethodWithoutTransactionImpl.class);

	@Autowired
	private ServicioEjemploRequiredNewTransaction srvEjemploConPropiaTransaccion;

	@Override
	@TransactionNever
	public String withoutTransaction() throws ServicioException {
		logger.info("ServicioEjemploMethodWithoutTransactionImpl.withoutTransaction()");
		return this.srvEjemploConPropiaTransaccion.operationWithNewTransaction();

	}
}
