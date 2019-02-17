package com.antoniojnavarro.naventory.app.jsf.beans;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.jsf.LazyDataModels.ClienteLazyDataModel;
import com.antoniojnavarro.naventory.app.security.services.api.ServicioAutenticacion;
import com.antoniojnavarro.naventory.app.security.services.dto.UsuarioAutenticado;
import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.model.entities.Cliente;
import com.antoniojnavarro.naventory.model.filters.ClienteSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioCliente;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

@Named("clientesBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class ClientesBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ClientesBean.class);

	// CAMPOS
	private boolean editing;
	private ExcelOptions excelOpt;
	private PDFOptions pdfOpt;
	// ENTITIES
	@Autowired
	ParamBean paramBean;
	private Cliente cliente;

	private Cliente selectedCliente;
	private UsuarioAutenticado usuarioAutenticado;
	private ClienteSearchFilter filtro;

	// LISTAS
	private ClienteLazyDataModel listaClientes;
	// SERVICIOS
	@Autowired
	private ServicioCliente srvCliente;
	@Autowired
	private ServicioAutenticacion srvAutenticacion;

	@PostConstruct
	public void init() {
		this.usuarioAutenticado = srvAutenticacion.getUserDetailsCurrentUserLogged();

		logger.info("Clientes.init()");
		inicilizarAtributos();
		customizationOptions();

	}

	public String cargar() {
		System.out.println("ok");
		return "antonio";
	}

	public void inicilizarAtributos() {
		this.filtro = new ClienteSearchFilter();
		filtro.setUsuario(this.usuarioAutenticado.getUsuario().getEmail());
		this.listaClientes = new ClienteLazyDataModel(filtro, srvCliente);
		this.editing = false;
		this.selectedCliente = new Cliente();
		this.cliente = new Cliente();
	}

	public void buscar() {
		this.listaClientes = new ClienteLazyDataModel(filtro, srvCliente);
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
		if (this.cliente != null) {
			paramBean.setParam(this.cliente);
			return Constantes.GO_TO_CLIENTE_DETAILS;
		} else
			return null;
	}

	public void iniciarSelectedCliente() {
		this.selectedCliente = null;
	}

	public void borrarCliente(Cliente cliente) {
		srvCliente.delete(cliente);
		addInfo("clientes.succesDelete");
		this.editing = false;
	}

	public void guardarCliente() {
		selectedCliente.setUsuario(usuarioAutenticado.getUsuario());
		srvCliente.saveOrUpdate(this.selectedCliente, true);
		super.closeDialog("clienteDetailsDialog");
		addInfo("clientes.succesNew");
		editing = false;
	}

	public void customizationOptions() {
		pdfOpt = new PDFOptions();
		pdfOpt.setFacetBgColor("#58ACFA");
		pdfOpt.setFacetFontColor("#FFFFFF");
		pdfOpt.setFacetFontStyle("BOLD");
		pdfOpt.setCellFontSize("12");
	}

	public void preProcesamientoPdf(Object document) throws IOException, BadElementException, DocumentException {
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);

		pdf.addAuthor(this.usuarioAutenticado.getUsuario().getEmail());
		String d = new SimpleDateFormat("dd/mm/YYYY").format(new Date()).toString();
		Paragraph p1 = new Paragraph("Informe de clientes | " + d);
		Font font = new Font(Font.TIMES_ROMAN, 18.0f, Font.BOLD, Color.RED);
		p1.setFont(font);
		Paragraph p2 = new Paragraph("Creado por el usuario " + this.usuarioAutenticado.getUsuario().getNombre() + " - "
				+ this.usuarioAutenticado.getUsuario().getEmail());
		p2.setFont(font);

		pdf.add(p1);
		pdf.add(p2);
		pdf.add(Chunk.NEWLINE);
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

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public ClienteSearchFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ClienteSearchFilter filtro) {
		this.filtro = filtro;
	}

	public ClienteLazyDataModel getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ClienteLazyDataModel listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ExcelOptions getExcelOpt() {
		return excelOpt;
	}

	public void setExcelOpt(ExcelOptions excelOpt) {
		this.excelOpt = excelOpt;
	}

	public PDFOptions getPdfOpt() {
		return pdfOpt;
	}

	public void setPdfOpt(PDFOptions pdfOpt) {
		this.pdfOpt = pdfOpt;
	}

}
