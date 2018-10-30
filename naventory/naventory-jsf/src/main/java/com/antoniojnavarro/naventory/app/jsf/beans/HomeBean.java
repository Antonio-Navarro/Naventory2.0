package com.antoniojnavarro.naventory.app.jsf.beans;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.model.entities.AlertaStock;
import com.antoniojnavarro.naventory.model.entities.Novedad;
import com.antoniojnavarro.naventory.services.api.ServicioAlertaStock;
import com.antoniojnavarro.naventory.services.api.ServicioCliente;
import com.antoniojnavarro.naventory.services.api.ServicioCompra;
import com.antoniojnavarro.naventory.services.api.ServicioNovedad;
import com.antoniojnavarro.naventory.services.api.ServicioProducto;
import com.antoniojnavarro.naventory.services.api.ServicioVenta;

@Named("homeBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class HomeBean extends MasterBean {
	private static final Logger logger = LoggerFactory.getLogger(HomeBean.class);
	private static final long serialVersionUID = 1L;
	private static final Integer LIMIT_NOVEDADES = 10;

	@Autowired
	private UsuarioAutenticado usuarioAuteticado;

	@Autowired
	private ServicioAlertaStock srvAlertaStock;

	@Autowired
	private ServicioProducto srvProducto;
	@Autowired
	private ServicioCompra srvCompra;
	@Autowired
	private ServicioVenta srvVenta;
	@Autowired
	private ServicioCliente srvCliente;
	@Autowired
	private ServicioNovedad srvNovedad;
	private List<AlertaStock> alertas;
	private List<Novedad> novedades;

	boolean exitenAlertas;
	private Integer numProductos;
	private Integer numCompras;
	private Integer numVentas;
	private Integer numClientes;
	private DonutChartModel donutFormasPago;
	private LineChartModel areaClientes;
	private LineChartModel areaVentas;
	private BarChartModel barGastosIngresos;
	private Double beneficio;

	@PostConstruct
	public void init() {
		logger.debug("Pasando por el init de home");
		this.usuarioAuteticado.isLoged();
		cargarAlertas();
		cargarNovedades();
		this.numProductos = srvProducto.findProductosByUsuario(this.usuarioAuteticado.getUsuario()).size();
		this.numCompras = srvCompra.findComprasByUsuario(this.usuarioAuteticado.getUsuario()).size();
		this.numVentas = srvVenta.findVentasByUsuario(this.usuarioAuteticado.getUsuario()).size();
		this.numClientes = srvCliente.findClientesByUsuario(this.usuarioAuteticado.getUsuario()).size();
		if (this.usuarioAuteticado.getUsuario() != null && this.usuarioAuteticado.getUsuario().getEmail() != null) {
			crearDonutFormasPago();
			crearAreaClientes();
			crearAreaVentas();
			crearBarGastosIngresos();
		}

	}

	private DonutChartModel iniciarDonutFormasPago() {
		DonutChartModel model = new DonutChartModel();

		Map<String, Number> donut = new LinkedHashMap<String, Number>();

		Object[] datosGraficaVentas = srvVenta.findFormasPagoGrafica(this.usuarioAuteticado.getUsuario().getEmail());
		for (int i = 0; i < datosGraficaVentas.length; i++) {
			Object[] datos = (Object[]) datosGraficaVentas[i];
			donut.put(datos[0].toString(), (Number) datos[1]);

		}

		model.addCircle(donut);
		model.setMouseoverHighlight(true);
		return model;

	}

	public void crearDonutFormasPago() {
		donutFormasPago = iniciarDonutFormasPago();
		donutFormasPago.setLegendPosition("s");
		donutFormasPago.setSliceMargin(5);
		donutFormasPago.setShowDataLabels(true);
		donutFormasPago.setDataFormat("value");
		donutFormasPago.setShadow(true);
		donutFormasPago.setExtender("customExtender");

	}

	public void crearAreaClientes() {
		areaClientes = new LineChartModel();

		LineChartSeries clientes = new LineChartSeries();
		clientes.setFill(true);
		clientes.setLabel("Clientes");

		Object[] datosGraficaClientes = srvCliente.findClientesGrafica(this.usuarioAuteticado.getUsuario().getEmail());
		for (int i = 0; i < datosGraficaClientes.length; i++) {
			Object[] datos = (Object[]) datosGraficaClientes[i];
			clientes.set(datos[0].toString(), (Number) datos[1]);

		}
		areaClientes.addSeries(clientes);
		areaClientes.setLegendPosition("ne");
		areaClientes.setStacked(true);
		areaClientes.setShowPointLabels(true);
		areaClientes.setSeriesColors("337ab7");
		areaClientes.setZoom(true);
		areaClientes.setExtender("customExtender");
		areaClientes.setAnimate(true);
		Axis xAxis = new CategoryAxis("Fecha");
		xAxis.setTickAngle(0);
		areaClientes.getAxes().put(AxisType.X, xAxis);
		Axis yAxis = areaClientes.getAxis(AxisType.Y);
		yAxis.setLabel("Cantidad");
		yAxis.setMin(0);
		yAxis.setMax(this.numClientes);
	}

	public BarChartModel iniciarBarGastosIngresos() {
		BarChartModel model = new BarChartModel();

		Double totalIng = 0.0;
		Double totalVent = 0.0;

		ChartSeries ingresos = new ChartSeries();
		ingresos.setLabel("Ingresos");
		List<Object> datosGraficaIngresos = srvVenta
				.getIngresosMensualesGrafica(this.usuarioAuteticado.getUsuario().getEmail());
		for (Object registro : datosGraficaIngresos) {
			Object[] dato = (Object[]) registro;
			ingresos.set(dato[0].toString(), (Number) dato[1]);
			totalVent = +(Double) dato[1];
		}

		ChartSeries gastos = new ChartSeries();
		gastos.setLabel("Gastos");
		List<Object> datosGraficaGastos = srvCompra
				.getGastosMensualesGrafica(this.usuarioAuteticado.getUsuario().getEmail());
		for (Object registro : datosGraficaGastos) {
			Object[] dato = (Object[]) registro;
			gastos.set(dato[0].toString(), (Number) dato[1]);
			totalIng = +(Double) dato[1];

		}
		model.addSeries(ingresos);
		model.addSeries(gastos);
		model.setAnimate(true);
		model.setExtender("customExtender");
		this.beneficio = totalIng - totalVent;
		return model;
	}

	public void crearBarGastosIngresos() {
		barGastosIngresos = iniciarBarGastosIngresos();

		barGastosIngresos.setLegendPosition("ne");

		Axis xAxis = barGastosIngresos.getAxis(AxisType.X);
		xAxis.setLabel("Meses");
		barGastosIngresos.setShowPointLabels(true);
		barGastosIngresos.setSeriesColors("5cb85c,f0ad4e");

		Axis yAxis = barGastosIngresos.getAxis(AxisType.Y);
		yAxis.setLabel("Totales");
		yAxis.setMin(0);
	}

	public void crearAreaVentas() {
		areaVentas = new LineChartModel();

		LineChartSeries ventas = new LineChartSeries();
		ventas.setFill(true);
		ventas.setLabel("Ventas");

		List<Object> datosGraficaVentas = srvVenta
				.getVentasMensualesGrafica(this.usuarioAuteticado.getUsuario().getEmail());
		for (Object registro : datosGraficaVentas) {
			Object[] dato = (Object[]) registro;
			ventas.set(dato[0].toString(), (Number) dato[1]);
		}

		areaVentas.addSeries(ventas);
		areaVentas.setLegendPosition("ne");
		areaVentas.setStacked(true);
		areaVentas.setShowPointLabels(true);
		areaVentas.setSeriesColors("d9534f");
		areaVentas.setExtender("customExtender");
		areaVentas.setZoom(true);
		areaVentas.setAnimate(true);
		Axis xAxis = new CategoryAxis("Meses");
		xAxis.setTickAngle(0);
		areaVentas.getAxes().put(AxisType.X, xAxis);
		Axis yAxis = areaVentas.getAxis(AxisType.Y);
		yAxis.setLabel("Cantidad");
		yAxis.setMin(0);
		yAxis.setMax(this.numVentas);
	}

	public UsuarioAutenticado getUsuarioAuteticado() {
		return usuarioAuteticado;
	}

	public void setUsuarioAuteticado(UsuarioAutenticado usuarioAuteticado) {
		this.usuarioAuteticado = usuarioAuteticado;
	}

	public void cargarAlertas() {
		alertas = this.srvAlertaStock.findAlertasByUsuario(this.usuarioAuteticado.getUsuario());
	}

	public void cargarNovedades() {
		novedades = this.srvNovedad.findNovedadesByUsuario(this.usuarioAuteticado.getUsuario(), LIMIT_NOVEDADES);
	}

	public List<AlertaStock> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<AlertaStock> alertas) {
		this.alertas = alertas;
	}

	public boolean isExitenAlertas() {
		return (this.alertas.size() > 0);

	}

	public void setExitenAlertas(boolean exitenAlertas) {
		this.exitenAlertas = exitenAlertas;
	}

	public Integer getNumProductos() {
		return numProductos;
	}

	public void setNumProductos(Integer numProductos) {
		this.numProductos = numProductos;
	}

	public Integer getNumCompras() {
		return numCompras;
	}

	public void setNumCompras(Integer numCompras) {
		this.numCompras = numCompras;
	}

	public Integer getNumVentas() {
		return numVentas;
	}

	public void setNumVentas(Integer numVentas) {
		this.numVentas = numVentas;
	}

	public Integer getNumClientes() {
		return numClientes;
	}

	public void setNumClientes(Integer numClientes) {
		this.numClientes = numClientes;
	}

	public LineChartModel getAreaClientes() {
		return areaClientes;
	}

	public void setAreaClientes(LineChartModel areaClientes) {
		this.areaClientes = areaClientes;
	}

	public DonutChartModel getDonutFormasPago() {
		return donutFormasPago;
	}

	public void setDonutFormasPago(DonutChartModel donutFormasPago) {
		this.donutFormasPago = donutFormasPago;
	}

	public List<Novedad> getNovedades() {
		return novedades;
	}

	public void setNovedades(List<Novedad> novedades) {
		this.novedades = novedades;
	}

	public LineChartModel getAreaVentas() {
		return areaVentas;
	}

	public void setAreaVentas(LineChartModel areaVentas) {
		this.areaVentas = areaVentas;
	}

	public BarChartModel getBarGastosIngresos() {
		return barGastosIngresos;
	}

	public void setBarGastosIngresos(BarChartModel barGastosIngresos) {
		this.barGastosIngresos = barGastosIngresos;
	}

	public Double getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(Double beneficio) {
		this.beneficio = beneficio;
	}
}
