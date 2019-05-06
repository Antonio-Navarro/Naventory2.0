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
import com.antoniojnavarro.naventory.app.security.services.api.ServicioAutenticacion;
import com.antoniojnavarro.naventory.app.security.services.dto.UsuarioAutenticado;
import com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto;
import com.antoniojnavarro.naventory.model.entities.Novedad;
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

	private UsuarioAutenticado usuarioAuteticado;

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

	@Autowired
	private ServicioAutenticacion srvAutenticacion;

	private List<Novedad> novedades;
	private String empresa;

	private Long numProductos;
	private Long numCompras;
	private Long numVentas;
	private Long numClientes;
	private DonutChartModel donutFormasPago;
	private LineChartModel areaClientes;
	private LineChartModel areaVentas;
	private BarChartModel barGastosIngresos;
	private Double beneficio;

	@PostConstruct
	public void init() {
		logger.debug("Pasando por el init de home");
		this.usuarioAuteticado = srvAutenticacion.getUserDetailsCurrentUserLogged();
		logger.info("Usuario autenticado: " + usuarioAuteticado.getUsername());
		cargarNovedades();
		this.numProductos = srvProducto.countByEmpresa(this.usuarioAuteticado.getUsuario().getEmpresa());
		this.numCompras = srvCompra.countByEmpresa(this.usuarioAuteticado.getUsuario().getEmpresa());
		this.numVentas = srvVenta.countByEmpresa(this.usuarioAuteticado.getUsuario().getEmpresa());
		this.numClientes = srvCliente.countByEmpresa(this.usuarioAuteticado.getUsuario().getEmpresa());
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

		List<GraficaGenericDto> datosGraficaVentas = srvVenta
				.findFormasPagoGrafica(this.usuarioAuteticado.getUsuario().getEmpresa());
		if (datosGraficaVentas == null || datosGraficaVentas.size() == 0) {
			donut.put("Sin datos", 0);
		} else {
			for (GraficaGenericDto registro : datosGraficaVentas) {
				donut.put(registro.getEtiqueta(), registro.getCantidad());
			}
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

	private Integer numMesesGastosIngresos = 1;

	public BarChartModel iniciarBarGastosIngresos() {
		BarChartModel model = new BarChartModel();

		Double totalIng = 0.0;
		Double totasGastos = 0.0;

		ChartSeries ingresos = new ChartSeries();
		ingresos.setLabel("Ingresos");

		List<GraficaGenericDto> datosGraficaIngresos = srvVenta
				.getIngresosMensualesGrafica(this.usuarioAuteticado.getUsuario().getEmpresa(), numMesesGastosIngresos);
		if (datosGraficaIngresos == null || datosGraficaIngresos.size() == 0) {
			ingresos.set("Sin datos", 0);

		} else {
			for (GraficaGenericDto registro : datosGraficaIngresos) {
				ingresos.set(registro.getEtiqueta(), registro.getCantidad());
				totalIng = +(Double) registro.getCantidad();
			}
		}

		ChartSeries gastos = new ChartSeries();
		gastos.setLabel("Gastos");
		List<GraficaGenericDto> datosGraficaGastos = srvCompra
				.getGastosMensualesGrafica(this.usuarioAuteticado.getUsuario().getEmpresa(), numMesesGastosIngresos);
		if (datosGraficaGastos == null || datosGraficaGastos.size() == 0) {
			gastos.set("Sin datos", 0);

		} else {
			for (GraficaGenericDto registro : datosGraficaGastos) {
				gastos.set(registro.getEtiqueta(), registro.getCantidad());
				totasGastos = +(Double) registro.getCantidad();
			}
		}

		model.addSeries(ingresos);
		model.addSeries(gastos);
		model.setAnimate(true);
		model.setExtender("customExtender");
		this.beneficio = totalIng - totasGastos;
		return model;
	}

	public void actualizarBarGastosIngresos(int numMeses) {
		numMesesGastosIngresos = numMeses;
		beneficio = 0.0;
		crearBarGastosIngresos();
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

	public void crearAreaClientes() {
		areaClientes = new LineChartModel();

		LineChartSeries clientes = new LineChartSeries();
		clientes.setFill(true);
		clientes.setLabel("Clientes");

		List<GraficaGenericDto> datosGraficaClientes = srvCliente
				.findClientesGrafica(this.usuarioAuteticado.getUsuario().getEmpresa().getCif());
		if (datosGraficaClientes == null || datosGraficaClientes.size() == 0) {
			clientes.set("Sin datos", 0);
		} else {
			for (GraficaGenericDto registro : datosGraficaClientes) {
				clientes.set(registro.getEtiqueta(), registro.getCantidad());

			}
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
		yAxis.setMax(this.numClientes + 1);
	}

	public void crearAreaVentas() {
		areaVentas = new LineChartModel();

		LineChartSeries ventas = new LineChartSeries();
		ventas.setFill(true);
		ventas.setLabel("Ventas");

		List<GraficaGenericDto> datosGraficaVentas = srvVenta
				.getVentasMensualesGrafica(this.usuarioAuteticado.getUsuario().getEmpresa());
		if (datosGraficaVentas == null || datosGraficaVentas.size() == 0) {
			ventas.set("Sin datos", 0);
		} else {
			for (GraficaGenericDto registro : datosGraficaVentas) {
				ventas.set(registro.getEtiqueta(), registro.getCantidad());
			}
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
		yAxis.setMax(this.numVentas + 1);
	}

	public void cargarNovedades() {
		novedades = this.srvNovedad.findNovedadesByEmpresa(this.usuarioAuteticado.getUsuario().getEmpresa(),
				LIMIT_NOVEDADES);
		if (novedades == null || novedades.size() == 0) {
			Novedad sinNov = new Novedad();
			sinNov.setNovedad("No hay novedades");
			novedades.add(sinNov);
		}
	}

	public Long getNumProductos() {
		return numProductos;
	}

	public void setNumProductos(Long numProductos) {
		this.numProductos = numProductos;
	}

	public Long getNumCompras() {
		return numCompras;
	}

	public void setNumCompras(Long numCompras) {
		this.numCompras = numCompras;
	}

	public Long getNumVentas() {
		return numVentas;
	}

	public void setNumVentas(Long numVentas) {
		this.numVentas = numVentas;
	}

	public Long getNumClientes() {
		return numClientes;
	}

	public void setNumClientes(Long numClientes) {
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

	public UsuarioAutenticado getUsuarioAuteticado() {
		return usuarioAuteticado;
	}

	public void setUsuarioAuteticado(UsuarioAutenticado usuarioAuteticado) {
		this.usuarioAuteticado = usuarioAuteticado;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Integer getNumMesesGastosIngresos() {
		return numMesesGastosIngresos;
	}

	public void setNumMesesGastosIngresos(Integer numMesesGastosIngresos) {
		this.numMesesGastosIngresos = numMesesGastosIngresos;
	}

}
