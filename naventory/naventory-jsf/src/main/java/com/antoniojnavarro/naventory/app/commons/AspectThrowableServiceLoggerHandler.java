package com.antoniojnavarro.naventory.app.commons;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class AspectThrowableServiceLoggerHandler implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AspectThrowableServiceLoggerHandler.class);

	@AfterThrowing(pointcut = "@within(org.springframework.stereotype.Service) && execution(public * *(..))", throwing = "ex")
	public void loggedException(JoinPoint jointPoint, Throwable ex) {
		logger.error("JoinPoint --> " + jointPoint);
		logger.error(ex.getMessage(), ex);
	}

	@PostConstruct
	public void init() {
		logger.info("Iniciando " + this.getClass().getSimpleName());
	}

	@PreDestroy
	public void destroy() {
		logger.info("Destruyendo " + this.getClass().getSimpleName());
	}
}