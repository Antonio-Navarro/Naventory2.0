package com.antoniojnavarro.naventory.app.jsf.beans;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.model.entities.Producto;

@Named("productoDetailsBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class ProductoDetailsBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ProductoDetailsBean.class);

	// CAMPOS
	// ENTITIES
	@Autowired
	ParamBean param;

	private Producto producto;
	@Autowired
	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	// SERVICIOS
	@PostConstruct
	public void init() {
		usuarioAutenticado.isLoged();
		this.producto = (Producto) param.getParam();
		
		logger.info("ProductoDetail.init()");
		

	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
