package com.antoniojnavarro.naventory.app.jsf.beans;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.event.FlowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.security.services.api.ServicioAutenticacion;
import com.antoniojnavarro.naventory.app.security.services.dto.UsuarioAutenticado;
import com.antoniojnavarro.naventory.model.entities.Categoria;
import com.antoniojnavarro.naventory.model.entities.Compra;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.entities.Proveedor;
import com.antoniojnavarro.naventory.services.api.ServicioAlertaStock;
import com.antoniojnavarro.naventory.services.api.ServicioCategoria;
import com.antoniojnavarro.naventory.services.api.ServicioCompra;
import com.antoniojnavarro.naventory.services.api.ServicioProducto;
import com.antoniojnavarro.naventory.services.api.ServicioProveedor;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

@Named("comprasBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class ComprasBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ComprasBean.class);

	// CAMPOS
	private boolean editing;
	private boolean skip;
	private ExcelOptions excelOpt;
	private PDFOptions pdfOpt;
	// ENTITIES
	@Autowired
	ParamBean paramBean;
	private Compra compra;

	private Compra selectedCompra;
	private Producto selectedProducto;

	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	private List<Compra> compras;
	private List<Compra> filteredCompras;
	private List<Proveedor> proveedores;
	private List<Producto> productos;
	private List<Categoria> categorias;

	// SERVICIOS
	@Autowired
	private ServicioCompra srvCompra;
	@Autowired
	private ServicioProveedor srvProveedor;
	@Autowired
	private ServicioProducto srvProducto;
	@Autowired
	private ServicioCategoria srvCategoria;
	@Autowired
	private ServicioAlertaStock srvAlertaStock;

	@Autowired
	private ServicioAutenticacion srvAutenticacion;

	@PostConstruct
	public void init() {
		logger.info("Compras.init()");
		this.usuarioAutenticado = srvAutenticacion.getUserDetailsCurrentUserLogged();
		inicilizarAtributos();
		cargarCompras();
		cargarProveedores();
		cargarProductos();
		cargarCategorias();
		customizationOptions();

	}

	public void inicilizarAtributos() {
		this.compras = null;
		this.editing = false;
		this.selectedCompra = new Compra();
		this.selectedProducto = new Producto();
		this.compra = new Compra();
	}

	public void newCompra() {
		this.editing = false;
		this.selectedCompra = new Compra();
		this.selectedProducto = new Producto();

	}

	public void existeCompra() {
		this.editing = false;
		this.selectedCompra = new Compra();

	}

	public void iniciarSelectedCompra() {
		this.selectedCompra = null;
	}

	public void cargarCompras() {
		this.compras = srvCompra.findComprasByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public void cargarProveedores() {
		this.proveedores = srvProveedor.findProveedoresByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public void cargarProductos() {
		this.productos = srvProducto.findProductosByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public void cargarCategorias() {
		this.categorias = srvCategoria.findCategoriasByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public void borrarCompra(Compra compra) {
		if (compra.getIdCompra() != null && compra.getIdCompra() > 0) {
			Producto p = srvCompra.findById(compra.getIdCompra()).getProducto();
			if (p != null) {
				p.setStock(p.getStock() - compra.getCantidad());
				srvProducto.saveOrUpdate(p, true);
				this.srvAlertaStock.comprobarAlerta(compra.getProducto());
			}

		}
		srvCompra.delete(compra);
		this.compras.remove(compra);
		addInfo("compras.succesDelete");
		this.editing = false;
	}

	public void guardarCompra(Boolean nuevoProducto) {

		selectedCompra.setUsuario(usuarioAutenticado.getUsuario());
		if (nuevoProducto) {
			selectedProducto.setProveedor(selectedCompra.getProveedor());
			selectedProducto.setStock(selectedCompra.getCantidad());
			selectedProducto.setUsuario(usuarioAutenticado.getUsuario());
			selectedCompra.setProducto(selectedProducto);
		}
		if (selectedCompra.getIdCompra() != null && selectedCompra.getIdCompra() > 0) {
			Compra v = srvCompra.findById(selectedCompra.getIdCompra());
			selectedCompra.getProducto().setStock(selectedCompra.getProducto().getStock() - v.getCantidad());
			srvProducto.saveOrUpdate(selectedCompra.getProducto(), true);
		}
		selectedCompra = srvCompra.calcularCompra(selectedCompra);
		selectedCompra = srvCompra.saveNewOrUpdate(selectedCompra, true, nuevoProducto);
		selectedCompra = srvCompra.findById(selectedCompra.getIdCompra());

		if (!editing) {
			this.compras.add(0, selectedCompra);
		}
		if (!nuevoProducto)
			super.closeDialog("compraExisteDetailsDialog");
		else
			super.closeDialog("compraDetailsDialog");

		addInfo("compras.succesNew");
		srvAlertaStock.comprobarAlertaRecepcionProducto(selectedCompra.getProducto());

		editing = false;
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "compraTab";
		} else {
			return event.getNewStep();
		}
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
		Paragraph p1 = new Paragraph("Informe de compras | " + d);
		Font font = new Font(Font.TIMES_ROMAN, 18.0f, Font.BOLD, Color.RED);
		p1.setFont(font);
		Paragraph p2 = new Paragraph("Creado por el usuario " + this.usuarioAutenticado.getUsuario().getNombre() + " - "
				+ this.usuarioAutenticado.getUsuario().getEmail());
		p2.setFont(font);

		pdf.add(p1);
		pdf.add(p2);
		pdf.add(Chunk.NEWLINE);
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Compra getSelectedCompra() {
		return selectedCompra;
	}

	public void setSelectedCompra(Compra selectedCompra) {
		this.selectedCompra = selectedCompra;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public List<Compra> getFilteredCompras() {
		return filteredCompras;
	}

	public void setFilteredCompras(List<Compra> filteredCompras) {
		this.filteredCompras = filteredCompras;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public Producto getSelectedProducto() {
		return selectedProducto;
	}

	public void setSelectedProducto(Producto selectedProducto) {
		this.selectedProducto = selectedProducto;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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

	public UsuarioAutenticado getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(UsuarioAutenticado usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}

}
