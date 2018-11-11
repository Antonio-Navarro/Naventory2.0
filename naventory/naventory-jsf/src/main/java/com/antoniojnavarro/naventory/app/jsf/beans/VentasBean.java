package com.antoniojnavarro.naventory.app.jsf.beans;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.app.util.SortOrderParseUtil;
import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.model.entities.Cliente;
import com.antoniojnavarro.naventory.model.entities.FormaPago;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.entities.Venta;
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
	private long numResults;
	private DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	// ENTITIES
	@Autowired
	ParamBean paramBean;
	private Venta venta;
	private Venta selectedVenta;
	private VentaSearchFilter ventaFilter;
	@Autowired
	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	private List<Venta> filteredVentas;
	private List<Cliente> clientes;
	private List<Producto> productos;
	private List<FormaPago> formasPago;
	private LazyDataModel<Venta> lazyModel;

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
		cargarClientes();
		cargarProductos();
		cargarFormasPago();
		customizationOptions();
		initLazyModel();
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
		this.editing = false;
		this.selectedVenta = new Venta();
		this.venta = new Venta();
		this.ventaFilter = new VentaSearchFilter();
		ventaFilter.setUsuario(this.usuarioAutenticado.getUsuario().getEmail());
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
		super.closeDialog("ventaDetailsDialog");
		addInfo("ventas.succesNew");
		String msjError = srvAlertaStock.comprobarAlerta(selectedVenta.getProducto());
		if (msjError != null) {
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

	public VentaSearchFilter getVentaFilter() {
		return ventaFilter;
	}

	public void setVentaFilter(VentaSearchFilter ventaFilter) {
		this.ventaFilter = ventaFilter;
	}

	public LazyDataModel<Venta> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Venta> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public long getNumResults() {
		return numResults;
	}

	public void setNumResults(long numResults) {
		this.numResults = numResults;
	}

	public void initLazyModel() {
		this.lazyModel = new LazyDataModel<Venta>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Venta getRowData(String rowKey) {
				return srvVenta.findById(Integer.parseInt(rowKey));
			}

			@Override
			public Object getRowKey(Venta object) {
				return object.getIdVenta().toString();
			}

			@Override
			public List<Venta> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				ventaFilter.setNombreProducto((String) filters.get("nombre"));
				ventaFilter.setNombreCliente((String) filters.get("cliente.nombre"));

				ventaFilter.setCantidad(
						(String) filters.get("cantidad") != null ? Integer.parseInt((String) filters.get("cantidad"))
								: null);
				try {
					ventaFilter.setFecha(
							(String) filters.get("fecha") != null ? sdf.parse((String) filters.get("fecha")) : null);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				PaginationResult<Venta> paginationResult = srvVenta.findBySearchFilterPagination(ventaFilter,
						pageSize > 0 ? (first / pageSize) + 1 : 1,
						pageSize > 0 ? pageSize : Constantes.DEFAULT_PAGE_SIZE, sortField,
						SortOrderParseUtil.parseSortOrderPrimefacesToSortOrderDao(sortOrder));
				numResults = paginationResult.getTotalResult();
				this.setRowCount(new Long(numResults).intValue());
				return paginationResult.getResult();
			}

		};
	}

}
