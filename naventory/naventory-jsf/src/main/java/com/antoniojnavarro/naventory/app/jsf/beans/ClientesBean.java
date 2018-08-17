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
import com.antoniojnavarro.naventory.model.entities.Cliente;
import com.antoniojnavarro.naventory.services.api.ServicioCliente;

@Named("clientesBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class ClientesBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ClientesBean.class);

	// CAMPOS
	private boolean editing;
	// ENTITIES
	@Autowired
	ParamBean paramBean;
	private Cliente cliente;

	private Cliente selectedCliente;
	@Autowired
	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	private List<Cliente> clientes;
	private List<Cliente> filteredClientes;
	// SERVICIOS
	@Autowired
	private ServicioCliente srvCliente;

	@PostConstruct
	public void init() {
		usuarioAutenticado.isLoged();
		logger.info("Clientes.init()");
		inicilizarAtributos();
		cargarClientes();

	}

	public String cargar() {
		System.out.println("ok");
		return "antonio";
	}

	public void inicilizarAtributos() {
		this.clientes = null;
		this.editing = false;
		this.selectedCliente = new Cliente();
		this.cliente = new Cliente();
	}

	public void newCliente() {
		this.editing = false;
		this.selectedCliente = new Cliente();
	}

	public void editarCliente(Cliente cliente) {
		this.selectedCliente = null;
		this.selectedCliente = cliente;
		this.editing = true;
	}

	public void onRowSelect(SelectEvent event) {
		this.cliente = (Cliente) event.getObject();
		paramBean.setParam(this.cliente);
		  try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("clienteDetails.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public String irADetalle(Cliente cliente) {
		this.cliente = cliente;
		if(this.cliente !=null) {
			paramBean.setParam(this.cliente);
			return Constantes.GO_TO_CLIENTE_DETAILS;
		}else
			return null;
	}

	public void iniciarSelectedCliente() {
		this.selectedCliente = null;
	}

	public void cargarClientes() {
		this.clientes = srvCliente.findClientesByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public void borrarCliente(Cliente cliente) {
		srvCliente.delete(cliente);
		this.clientes.remove(cliente);
		addInfo("clientes.succesDelete");
		this.editing = false;
	}

	public void guardarCliente() {
		selectedCliente.setUsuario(usuarioAutenticado.getUsuario());
		srvCliente.saveOrUpdate(this.selectedCliente, true);
		if (!editing) {
			this.clientes.add(selectedCliente);
		}
		super.closeDialog("clienteDetailsDialog");
		addInfo("clientes.succesNew");
		editing = false;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getSelectedCliente() {
		return selectedCliente;
	}

	public void setSelectedCliente(Cliente selectedCliente) {
		this.selectedCliente = selectedCliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public List<Cliente> getFilteredClientes() {
		return filteredClientes;
	}

	public void setFilteredClientes(List<Cliente> filteredClientes) {
		this.filteredClientes = filteredClientes;
	}

}
