package com.antoniojnavarro.naventory.app.jsf.beans;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.model.entities.Proveedor;

@Named("proveedorDetailsBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class ProveedorDetailsBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ProveedorDetailsBean.class);

	// CAMPOS
	// ENTITIES
	@Autowired
	ParamBean param;

	private Proveedor proveedor;
	@Autowired
	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	// SERVICIOS
	@PostConstruct
	public void init() {
		usuarioAutenticado.isLoged();
		this.proveedor = (Proveedor) param.getParam();
		
		logger.info("ProveedorDetail.init()");
		

	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}
