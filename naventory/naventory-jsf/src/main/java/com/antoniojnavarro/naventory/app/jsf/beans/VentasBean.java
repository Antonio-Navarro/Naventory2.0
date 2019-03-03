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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.jsf.LazyDataModels.ClienteLazyDataModel;
import com.antoniojnavarro.naventory.app.jsf.LazyDataModels.VentaLazyDataModel;
import com.antoniojnavarro.naventory.app.security.services.api.ServicioAutenticacion;
import com.antoniojnavarro.naventory.app.security.services.dto.UsuarioAutenticado;
import com.antoniojnavarro.naventory.model.entities.Cliente;
import com.antoniojnavarro.naventory.model.entities.FormaPago;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.entities.Venta;
import com.antoniojnavarro.naventory.model.filters.ClienteSearchFilter;
import com.antoniojnavarro.naventory.model.filters.VentaSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioAlertaStock;
import com.antoniojnavarro.naventory.services.api.ServicioCliente;
import com.antoniojnavarro.naventory.services.api.ServicioFormaPago;
import com.antoniojnavarro.naventory.services.api.ServicioProducto;
import com.antoniojnavarro.naventory.services.api.ServicioVenta;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

@Named("ventasBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class VentasBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(VentasBean.class);

	// CAMPOS
	private boolean editing;
	private ExcelOptions excelOpt;
	private PDFOptions pdfOpt;

	// ENTITIES
	@Autowired
	ParamBean paramBean;
	private Venta selectedVenta;
	private VentaSearchFilter filtro;
	private ClienteSearchFilter filtroClientes;

	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	private List<Venta> filteredVentas;
	private ClienteLazyDataModel clientes;
	private List<Producto> productos;
	private List<FormaPago> formasPago;
	private VentaLazyDataModel listaVentas;

	// SERVICIOS
	@Autowired
	private ServicioVenta srvVenta;
	@Autowired
	private ServicioCliente srvCliente;
	@Autowired
	private ServicioProducto srvProducto;
	@Autowired
	private ServicioFormaPago srvFormaPago;
	@Autowired
	private ServicioAlertaStock srvAlertaStock;
	@Autowired
	private ServicioAutenticacion srvAutenticacion;

	@PostConstruct
	public void init() {
		this.usuarioAutenticado = srvAutenticacion.getUserDetailsCurrentUserLogged();
		logger.info("Prooveedores.init()");
		inicilizarAtributos();
		cargarClientes();
		cargarProductos();
		cargarFormasPago();
		customizationOptions();

	}

	public void inicilizarAtributos() {
		this.editing = false;
		iniciarSelectedVenta();
		this.filtro = new VentaSearchFilter();
		filtro.setUsuario(this.usuarioAutenticado.getUsuario().getEmail());
		this.listaVentas = new VentaLazyDataModel(filtro, srvVenta);
	}

	public void buscar() {
		this.listaVentas = new VentaLazyDataModel(filtro, srvVenta);
	}

	public void newVenta() {
		this.editing = false;
		iniciarSelectedVenta();
	}

	public void editarVenta(Venta venta) {
		this.selectedVenta = null;
		this.selectedVenta = venta;
		this.editing = true;
	}

	public void iniciarSelectedVenta() {
		this.selectedVenta = new Venta();
		selectedVenta.setCliente(new Cliente());
		filtroClientes = new ClienteSearchFilter();
		filtroClientes.setUsuario(this.usuarioAutenticado.getUsuario().getEmail());

	}

	public void cargarClientes() {
		FacesContext context = FacesContext.getCurrentInstance();
		@SuppressWarnings("rawtypes")
		Map map = context.getExternalContext().getRequestParameterMap();
		filtroClientes.setNombre((String) map.get("myJSValue"));
		filtroClientes.setUsuario(this.usuarioAutenticado.getUsuario().getEmail());
		this.clientes = new ClienteLazyDataModel(filtroClientes, srvCliente);
	}

	public void cargarFormasPago() {
		this.formasPago = srvFormaPago.findFormasPagoByUsuario();
	}

	public void cargarProductos() {
		this.productos = srvProducto.findProductosByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public void borrarVenta(Venta venta) {
		if (venta.getIdVenta() != null && venta.getIdVenta() > 0) {
			Producto p = srvVenta.findById(venta.getIdVenta()).getProducto();
			if (p != null) {
				p.setStock(p.getStock() + venta.getCantidad());
				srvProducto.saveOrUpdate(p, true);
				if (srvAlertaStock.findAlertaByUsuarioAndProducto(this.usuarioAutenticado.getUsuario(), p) != null
						&& p.getStock() > p.getStockMin()) {
					srvAlertaStock.delete(
							srvAlertaStock.findAlertaByUsuarioAndProducto(this.usuarioAutenticado.getUsuario(), p));
				}
			}
		}
		srvVenta.delete(venta);
		addInfo("ventas.succesDelete");
		this.editing = false;
	}

	public void guardarVenta() {

		selectedVenta.setUsuario(usuarioAutenticado.getUsuario());
		if (editing && selectedVenta.getIdVenta() != null && selectedVenta.getIdVenta() > 0) {
			Venta v = srvVenta.findById(selectedVenta.getIdVenta());
			Producto p = selectedVenta.getProducto();
			if (p != null) {
				p.setStock(selectedVenta.getProducto().getStock() + v.getCantidad());
				srvProducto.saveOrUpdate(selectedVenta.getProducto(), true);
			}

		}
		selectedVenta = srvVenta.calcularVenta(selectedVenta);
		selectedVenta = srvVenta.saveOrUpdate(selectedVenta, true);
		selectedVenta = srvVenta.findById(selectedVenta.getIdVenta());
		super.closeDialog("ventaDetailsDialog");
		addInfo("ventas.succesNew");
		String msjError = srvAlertaStock.comprobarAlerta(selectedVenta.getProducto());
		if (msjError != null) {
			addError(msjError, selectedVenta.getProducto().getNombre(),
					selectedVenta.getProducto().getStock().toString(), selectedVenta.getProducto().getUnidad());
		}
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
		Paragraph p1 = new Paragraph("Informe de ventas | " + d);
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

	public List<Venta> getFilteredVentas() {
		return filteredVentas;
	}

	public void setFilteredVentas(List<Venta> filteredVentas) {
		this.filteredVentas = filteredVentas;
	}

	public ClienteLazyDataModel getClientes() {
		return clientes;
	}

	public void setClientes(ClienteLazyDataModel clientes) {
		this.clientes = clientes;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<FormaPago> getFormasPago() {
		return formasPago;
	}

	public void setFormasPago(List<FormaPago> formasPago) {
		this.formasPago = formasPago;
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

	public VentaSearchFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(VentaSearchFilter ventaFilter) {
		this.filtro = ventaFilter;
	}

	public VentaLazyDataModel getListaVentas() {
		return listaVentas;
	}

	public void setListaVentas(VentaLazyDataModel listaVentas) {
		this.listaVentas = listaVentas;
	}

	public ClienteSearchFilter getFiltroClientes() {
		return filtroClientes;
	}

	public void setFiltroClientes(ClienteSearchFilter filtroClientes) {
		this.filtroClientes = filtroClientes;
	}

}
