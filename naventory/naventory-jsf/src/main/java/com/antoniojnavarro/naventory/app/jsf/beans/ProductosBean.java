package com.antoniojnavarro.naventory.app.jsf.beans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.model.entities.Categoria;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.entities.Proveedor;
import com.antoniojnavarro.naventory.services.api.ServicioCategoria;
import com.antoniojnavarro.naventory.services.api.ServicioProducto;
import com.antoniojnavarro.naventory.services.api.ServicioProveedor;

@Named("productosBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class ProductosBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ProductosBean.class);

	// CAMPOS
	private boolean editing;
	private Float totalInventario;
	// ENTITIES
	@Autowired
	ParamBean paramBean;
	private Producto producto; 

	private Producto selectedProducto;
	@Autowired
	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	private List<Producto> productos;
	private List<Producto> filteredProductos;
	private List<Categoria> categorias;
	private List<Proveedor> proveedores;

	// SERVICIOS
	@Autowired
	private ServicioProducto srvProducto;
	@Autowired
	private ServicioCategoria srvCategoria;
	@Autowired
	private ServicioProveedor srvProveedor;

	@PostConstruct
	public void init() {
		usuarioAutenticado.isLoged();
		logger.info("Prooveedores.init()");
		inicilizarAtributos();
		cargarProductos();
		cargarCategorias();
		cargarProveedores();
		cargarTotalInventario();
	}

	public void inicilizarAtributos() {
		this.productos = null;
		this.editing = false;
		this.selectedProducto = new Producto();
		this.producto = new Producto();
	}

	public void newProducto() {
		this.editing = false;
		this.selectedProducto = new Producto();
	}

	public void editarProducto(Producto producto) {
		this.selectedProducto = null;
		this.selectedProducto = producto;
		this.editing = true;
	}

	public void onRowSelect(SelectEvent event) {
		this.producto = (Producto) event.getObject();
		paramBean.setParam(this.producto);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("productoDetails.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String irADetalle(Producto producto) {
		this.producto = producto;
		if (this.producto != null) {
			paramBean.setParam(producto);
			return Constantes.GO_TO_PRODUCTO_DETAILS;
		} else
			return null;
	}

	public void iniciarSelectedProducto() {
		this.selectedProducto = null;
	}

	public void cargarTotalInventario() {
		this.totalInventario = srvProducto.getTotalInventario();
	}
	
	public void cargarProductos() {
		this.productos = srvProducto.findProductosByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public void cargarCategorias() {
		this.categorias = srvCategoria.findCategoriasByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public void cargarProveedores() {
		this.proveedores = srvProveedor.findProveedoresByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public void borrarProducto(Producto producto) {

		srvProducto.delete(producto);
		this.productos.remove(producto);
		cargarTotalInventario();
		addInfo("productos.succesDelete");
		this.editing = false;
	}

	public void guardarProducto() {

		selectedProducto.setUsuario(usuarioAutenticado.getUsuario());
		if(!editing) {
			srvProducto.validateSKU(selectedProducto.getSku());
		}
		srvProducto.saveOrUpdate(this.selectedProducto, true);
		if (!editing) {
			this.productos.add(selectedProducto);
		}
		cargarTotalInventario();
		super.closeDialog("productoDetailsDialog");
		addInfo("productos.succesNew");
		editing = false;
	}

	
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Producto getSelectedProducto() {
		return selectedProducto;
	}

	public void setSelectedProducto(Producto selectedProducto) {
		this.selectedProducto = selectedProducto;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public List<Producto> getFilteredProductos() {
		return filteredProductos;
	}

	public void setFilteredProductos(List<Producto> filteredProductos) {
		this.filteredProductos = filteredProductos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public Float getTotalInventario() {
		return totalInventario;
	}

	public void setTotalInventario(Float totalInventario) {
		this.totalInventario = totalInventario;
	}

}
