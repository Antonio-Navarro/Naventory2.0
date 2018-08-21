package com.antoniojnavarro.naventory.app.jsf.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.model.entities.Cliente;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.entities.Venta;
import com.antoniojnavarro.naventory.services.api.ServicioCliente;
import com.antoniojnavarro.naventory.services.api.ServicioProducto;
import com.antoniojnavarro.naventory.services.api.ServicioVenta;

@Named("ventasBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class VentasBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(VentasBean.class);

	// CAMPOS
	private boolean editing;
	// ENTITIES
	@Autowired
	ParamBean paramBean;
	private Venta venta; 

	private Venta selectedVenta;
	@Autowired
	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	private List<Venta> ventas;
	private List<Venta> filteredVentas;
	private List<Cliente> clientes;
	private List<Producto> productos;

	// SERVICIOS
	@Autowired
	private ServicioVenta srvVenta;
	@Autowired
	private ServicioCliente srvCliente;
	@Autowired
	private ServicioProducto srvProducto;

	@PostConstruct
	public void init() {
		usuarioAutenticado.isLoged();
		logger.info("Prooveedores.init()");
		inicilizarAtributos();
		cargarVentas();
		cargarClientes();
		cargarProductos();
	}

	public void inicilizarAtributos() {
		this.ventas = null;
		this.editing = false;
		this.selectedVenta = new Venta();
		this.venta = new Venta();
	}

	public void newVenta() {
		this.editing = false;
		this.selectedVenta = new Venta();
	}

	public void editarVenta(Venta venta) {
		this.selectedVenta = null;
		this.selectedVenta = venta;
		this.editing = true;
	}


	public void iniciarSelectedVenta() {
		this.selectedVenta = null;
	}


	public void cargarVentas() {
		this.ventas = srvVenta.findVentasByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public void cargarClientes() {
		this.clientes = srvCliente.findClientesByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public void cargarProductos() {
		this.productos = srvProducto.findProductosByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public void borrarVenta(Venta venta) {

		srvVenta.delete(venta);
		this.ventas.remove(venta);
		addInfo("ventas.succesDelete");
		this.editing = false;
	}

	public void guardarVenta() {

		selectedVenta.setUsuario(usuarioAutenticado.getUsuario());
		srvVenta.saveOrUpdate(this.selectedVenta, true);
		if (!editing) {
			this.ventas.add(selectedVenta);
		}
		super.closeDialog("ventaDetailsDialog");
		addInfo("ventas.succesNew");
		editing = false;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Venta getSelectedVenta() {
		return selectedVenta;
	}

	public void setSelectedVenta(Venta selectedVenta) {
		this.selectedVenta = selectedVenta;
	}

	public UsuarioAutenticado getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(UsuarioAutenticado usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public List<Venta> getFilteredVentas() {
		return filteredVentas;
	}

	public void setFilteredVentas(List<Venta> filteredVentas) {
		this.filteredVentas = filteredVentas;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	

}
