package com.antoniojnavarro.naventory.app.jsf.beans;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.antoniojnavarro.naventory.app.security.services.api.ServicioAutenticacion;
import com.antoniojnavarro.naventory.app.security.services.dto.UsuarioAutenticado;
import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.model.entities.Proveedor;
import com.antoniojnavarro.naventory.services.api.ServicioProveedor;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

@Named("proveedoresBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class ProveedoresBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ProveedoresBean.class);

	// CAMPOS
	private boolean editing;
	private ExcelOptions excelOpt;
	private PDFOptions pdfOpt;
	// ENTITIES
	@Autowired
	ParamBean paramBean;
	private Proveedor proveedor;
	private Proveedor selectedProveedor;
	private UsuarioAutenticado usuarioAutenticado;

	// LISTAS
	private List<Proveedor> proveedores;
	private List<Proveedor> filteredProveedores;
	// SERVICIOS
	@Autowired
	private ServicioProveedor srvProveedor;
	@Autowired
	private ServicioAutenticacion srvAutenticacion;

	@PostConstruct
	public void init() {
		this.usuarioAutenticado = srvAutenticacion.getUserDetailsCurrentUserLogged();
		logger.info("Prooveedores.init()");
		inicilizarAtributos();
		cargarProveedores();
		customizationOptions();
	}

	public void inicilizarAtributos() {
		this.proveedores = null;
		this.editing = false;
		this.selectedProveedor = new Proveedor();
		this.proveedor = new Proveedor();
	}

	public void newProveedor() {
		this.editing = false;
		this.selectedProveedor = new Proveedor();
	}

	public void editarProveedor(Proveedor proveedor) {
		this.selectedProveedor = null;
		this.selectedProveedor = proveedor;
		this.editing = true;
	}

	public void onRowSelect(SelectEvent event) {
		this.proveedor = (Proveedor) event.getObject();
		paramBean.setParam(this.proveedor);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("proveedorDetails.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String irADetalle(Proveedor proveedor) {
		this.proveedor = proveedor;
		if (this.proveedor != null) {
			paramBean.setParam(proveedor);
			return Constantes.GO_TO_PROVEEDOR_DETAILS;
		} else
			return null;
	}

	public void iniciarSelectedProveedor() {
		this.selectedProveedor = null;
	}

	public void cargarProveedores() {
		this.proveedores = srvProveedor.findProveedoresByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public void borrarProveedor(Proveedor proveedor) {

		srvProveedor.delete(proveedor);
		this.proveedores.remove(proveedor);
		addInfo("proveedores.succesDelete");
		this.editing = false;
	}

	public void guardarProveedor() {

		selectedProveedor.setUsuario(usuarioAutenticado.getUsuario());
		srvProveedor.saveOrUpdate(this.selectedProveedor, true);
		if (!editing) {
			this.proveedores.add(selectedProveedor);
		}
		super.closeDialog("proveedorDetailsDialog");
		addInfo("proveedores.succesNew");
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
		Paragraph p1 = new Paragraph("Informe de proveedores | " + d);
		Font font = new Font(Font.TIMES_ROMAN, 18.0f, Font.BOLD, Color.RED);
		p1.setFont(font);
		Paragraph p2 = new Paragraph("Creado por el usuario " + this.usuarioAutenticado.getUsuario().getNombre() + " - "
				+ this.usuarioAutenticado.getUsuario().getEmail());
		p2.setFont(font);

		pdf.add(p1);
		pdf.add(p2);
		pdf.add(Chunk.NEWLINE);
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Proveedor getSelectedProveedor() {
		return selectedProveedor;
	}

	public void setSelectedProveedor(Proveedor selectedProveedor) {
		this.selectedProveedor = selectedProveedor;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public List<Proveedor> getFilteredProveedores() {
		return filteredProveedores;
	}

	public void setFilteredProveedores(List<Proveedor> filteredProveedores) {
		this.filteredProveedores = filteredProveedores;
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
