package com.antoniojnavarro.naventory.app.jsf.beans;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.event.FlowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.jsf.LazyDataModels.CompraLazyDataModel;
import com.antoniojnavarro.naventory.app.jsf.LazyDataModels.ProveedorLazyDataModel;
import com.antoniojnavarro.naventory.app.security.services.api.ServicioAutenticacion;
import com.antoniojnavarro.naventory.app.security.services.dto.UsuarioAutenticado;
import com.antoniojnavarro.naventory.model.entities.Categoria;
import com.antoniojnavarro.naventory.model.entities.Compra;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.entities.Proveedor;
import com.antoniojnavarro.naventory.model.filters.CompraSearchFilter;
import com.antoniojnavarro.naventory.model.filters.ProveedorSearchFilter;
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
	private CompraSearchFilter filtro;
	private ProveedorSearchFilter filtroProveedores;

	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	private CompraLazyDataModel listaCompras;
	private List<Compra> filteredCompras;
	private ProveedorLazyDataModel proveedores;
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
		cargarProveedores();
		cargarProductos();
		cargarCategorias();
		customizationOptions();

	}

	public void inicilizarAtributos() {
		this.editing = false;
		this.filtro = new CompraSearchFilter();
		filtro.setUsuario(this.usuarioAutenticado.getUsuario().getEmail());
		this.listaCompras = new CompraLazyDataModel(filtro, srvCompra);
		inicializarCompra();
		this.compra = new Compra();
	}

	public void buscar() {
		this.listaCompras = new CompraLazyDataModel(filtro, srvCompra);
	}

	public void inicializarCompra() {
		this.selectedCompra = new Compra();
		this.selectedCompra.setProveedor(new Proveedor());
		this.selectedProducto = new Producto();
		filtroProveedores = new ProveedorSearchFilter();
		filtroProveedores.setUsuario(this.usuarioAutenticado.getUsuario().getEmail());
	}

	public void newCompra() {
		this.editing = false;
		inicializarCompra();
	}

	public void existeCompra() {
		this.editing = false;
		inicializarCompra();

	}

	public void cargarProveedores() {

		FacesContext context = FacesContext.getCurrentInstance();
		@SuppressWarnings("rawtypes")
		Map map = context.getExternalContext().getRequestParameterMap();
		filtroProveedores.setNombre((String) map.get("myJSValue"));
		filtroProveedores.setUsuario(this.usuarioAutenticado.getUsuario().getEmail());
		this.proveedores = new ProveedorLazyDataModel(filtroProveedores, srvProveedor);
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

	public CompraSearchFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(CompraSearchFilter filtro) {
		this.filtro = filtro;
	}

	public CompraLazyDataModel getListaCompras() {
		return listaCompras;
	}

	public void setListaCompras(CompraLazyDataModel listaCompras) {
		this.listaCompras = listaCompras;
	}

}
