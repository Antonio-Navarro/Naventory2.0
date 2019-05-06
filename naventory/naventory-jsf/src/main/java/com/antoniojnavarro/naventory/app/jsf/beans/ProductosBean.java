package com.antoniojnavarro.naventory.app.jsf.beans;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.jsf.LazyDataModels.ProductoLazyDataModel;
import com.antoniojnavarro.naventory.app.jsf.LazyDataModels.ProveedorLazyDataModel;
import com.antoniojnavarro.naventory.app.security.services.api.ServicioAutenticacion;
import com.antoniojnavarro.naventory.app.security.services.dto.UsuarioAutenticado;
import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.model.entities.Categoria;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.entities.Proveedor;
import com.antoniojnavarro.naventory.model.filters.ProductoSearchFilter;
import com.antoniojnavarro.naventory.model.filters.ProveedorSearchFilter;
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
	private ProductoSearchFilter filtro;
	private Producto selectedProducto;
	private ProveedorSearchFilter filtroProveedores;

	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	private ProductoLazyDataModel listaProductos;

	private List<Categoria> categorias;
	private ProveedorLazyDataModel proveedores;

	// SERVICIOS
	@Autowired
	private ServicioProducto srvProducto;
	@Autowired
	private ServicioCategoria srvCategoria;
	@Autowired
	private ServicioProveedor srvProveedor;
	@Autowired
	private ServicioAutenticacion srvAutenticacion;

	@PostConstruct
	public void init() {
		this.usuarioAutenticado = srvAutenticacion.getUserDetailsCurrentUserLogged();

		logger.info("Prooveedores.init()");
		inicilizarAtributos();
		cargarCategorias();
		cargarProveedores();
		cargarTotalInventario();
	}

	public void inicilizarAtributos() {

		this.editing = false;
		iniciarSelectedProducto();
		this.producto = new Producto();
		this.filtro = new ProductoSearchFilter();
		filtro.setEmpresa(this.usuarioAutenticado.getUsuario().getEmpresa().getCif());
		listaProductos = new ProductoLazyDataModel(filtro, srvProducto);
	}

	public void buscar() {
		listaProductos = new ProductoLazyDataModel(filtro, srvProducto);
	}

	public void newProducto() {
		this.editing = false;
		iniciarSelectedProducto();
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
		this.selectedProducto = new Producto();
		this.selectedProducto.setProveedor(new Proveedor());
		filtroProveedores = new ProveedorSearchFilter();
		filtroProveedores.setEmpresa(this.usuarioAutenticado.getUsuario().getEmpresa().getCif());
	}

	public void cargarTotalInventario() {
		this.totalInventario = srvProducto.getTotalInventario(this.usuarioAutenticado.getUsuario().getEmpresa());
	}

	public void cargarCategorias() {
		this.categorias = srvCategoria.findCategoriasByEmpresa(this.usuarioAutenticado.getUsuario().getEmpresa());
	}

	public void cargarProveedores() {

		FacesContext context = FacesContext.getCurrentInstance();
		@SuppressWarnings("rawtypes")
		Map map = context.getExternalContext().getRequestParameterMap();
		filtroProveedores.setNombre((String) map.get("myJSValue"));
		filtroProveedores.setEmpresa(this.usuarioAutenticado.getUsuario().getEmpresa().getCif());
		this.proveedores = new ProveedorLazyDataModel(filtroProveedores, srvProveedor);
	}

	public void borrarProducto(Producto producto) {

		srvProducto.delete(producto);
		cargarTotalInventario();
		addInfo("productos.succesDelete");
		this.editing = false;
	}

	public void guardarProducto() {

		selectedProducto.setEmpresa(usuarioAutenticado.getUsuario().getEmpresa());
		if (!editing) {
			srvProducto.validateSKU(selectedProducto.getSku());
		}
		srvProducto.saveOrUpdate(this.selectedProducto, true);
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

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public ProveedorSearchFilter getFiltroProveedores() {
		return filtroProveedores;
	}

	public void setFiltroProveedores(ProveedorSearchFilter filtroProveedores) {
		this.filtroProveedores = filtroProveedores;
	}

	public ProveedorLazyDataModel getProveedores() {
		return proveedores;
	}

	public void setProveedores(ProveedorLazyDataModel proveedores) {
		this.proveedores = proveedores;
	}

	public Float getTotalInventario() {
		return totalInventario;
	}

	public void setTotalInventario(Float totalInventario) {
		this.totalInventario = totalInventario;
	}

	public ProductoSearchFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ProductoSearchFilter filtro) {
		this.filtro = filtro;
	}

	public ProductoLazyDataModel getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ProductoLazyDataModel listaProductos) {
		this.listaProductos = listaProductos;
	}

}
