package com.antoniojnavarro.naventory.app.jsf.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;

@Named("ejemploBean")
@Scope(value=PFScope.VIEW_SCOPED)
public class EjemploBean extends MasterBean {
	
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init(){ }
	
	@PreDestroy
	public void destroy(){ }

}
