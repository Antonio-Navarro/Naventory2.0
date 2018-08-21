package com.antoniojnavarro.naventory.app.jsf.beans;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.model.entities.Cliente;

@Named("clienteDetailsBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class ClienteDetailsBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ClienteDetailsBean.class);

	// CAMPOS
	// ENTITIES
	@Autowired
	ParamBean param;

	private Cliente cliente;
	@Autowired
	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	// SERVICIOS
	@PostConstruct
	public void init() {
		usuarioAutenticado.isLoged();
		this.cliente = (Cliente) param.getParam();
		
		logger.info("ClienteDetail.init()");
		

	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
