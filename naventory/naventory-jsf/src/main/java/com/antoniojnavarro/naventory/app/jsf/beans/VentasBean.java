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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.model.entities.Cliente;
import com.antoniojnavarro.naventory.model.entities.FormaPago;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.entities.Venta;
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
	private Venta venta;
	private Venta selectedVenta;
	@Autowired
	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	private List<Venta> ventas;
	private List<Venta> filteredVentas;
	private List<Cliente> clientes;
	private List<Producto> productos;
	private List<FormaPago> formasPago;

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

	@PostConstruct
	public void init() {
		usuarioAutenticado.isLoged();
		logger.info("Prooveedores.init()");
		inicilizarAtributos();
		cargarVentas();
		cargarClientes();
		cargarProductos();
		cargarFormasPago();
		customizationOptions();
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

	public void cargarFormasPago() {
		this.formasPago = srvFormaPago.findFormasPagoByUsuario();
	}

	public void cargarProductos() {
		this.productos = srvProducto.findProductosByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public void borrarVenta(Venta venta) {
		if (venta.getIdVenta() != null && venta.getIdVenta() > 0) {
			Producto p = srvVenta.findById(venta.getIdVenta()).getProducto();
			p.setStock(p.getStock() + venta.getCantidad());
			srvProducto.saveOrUpdate(p, true);
			if (srvAlertaStock.findAlertaByUsuarioAndProducto(this.usuarioAutenticado.getUsuario(), p) != null
					&& p.getStock() > p.getStockMin()) {
				srvAlertaStock
						.delete(srvAlertaStock.findAlertaByUsuarioAndProducto(this.usuarioAutenticado.getUsuario(), p));
			}
		}
		srvVenta.delete(venta);
		this.ventas.remove(venta);
		addInfo("ventas.succesDelete");
		this.editing = false;
	}

	public void guardarVenta() {

		selectedVenta.setUsuario(usuarioAutenticado.getUsuario());
		if (editing && selectedVenta.getIdVenta() != null && selectedVenta.getIdVenta() > 0) {
			Venta v = srvVenta.findById(selectedVenta.getIdVenta());
			selectedVenta.getProducto().setStock(selectedVenta.getProducto().getStock() + v.getCantidad());
			srvProducto.saveOrUpdate(selectedVenta.getProducto(), true);
		}
		selectedVenta = srvVenta.calcularVenta(selectedVenta);
		selectedVenta = srvVenta.saveOrUpdate(selectedVenta, true);
		selectedVenta = srvVenta.findById(selectedVenta.getIdVenta());
		if (!editing) {
			this.ventas.add(0, selectedVenta);
		}
		super.closeDialog("ventaDetailsDialog");
		addInfo("ventas.succesNew");
		String msjError = srvAlertaStock.comprobarAlerta(selectedVenta.getProducto());
		if (msjError != null) {
			updateComponent("panelTop");
			addError(msjError, selectedVenta.getProducto().getNombre(),
					selectedVenta.getProducto().getStock().toString(), selectedVenta.getProducto().getUnidad());
		}
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

}