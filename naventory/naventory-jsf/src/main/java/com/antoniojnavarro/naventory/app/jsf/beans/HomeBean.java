package com.antoniojnavarro.naventory.app.jsf.beans;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
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
import com.antoniojnavarro.naventory.model.entities.Evento;
import com.antoniojnavarro.naventory.model.entities.Novedad;
import com.antoniojnavarro.naventory.services.api.ServicioCliente;
import com.antoniojnavarro.naventory.services.api.ServicioCompra;
import com.antoniojnavarro.naventory.services.api.ServicioEvento;
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
	ServicioEvento srvEvento;
	@Autowired
	private ServicioVenta srvVenta;
	@Autowired
	private ServicioCliente srvCliente;
	@Autowired
	private ServicioNovedad srvNovedad;

	@Autowired
	private ServicioAutenticacion srvAutenticacion;

	private List<Novedad> novedades;
	private List<Evento> eventos;
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
	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();

	@PostConstruct
	public void init() {
		logger.debug("Pasando por el init de home");
		this.usuarioAuteticado = srvAutenticacion.getUserDetailsCurrentUserLogged();
		logger.info("Usuario autenticado: " + usuarioAuteticado.getUsername());
		cargarNovedades();
		this.numProductos = srvProducto.countByUsuario(this.usuarioAuteticado.getUsuario());
		this.numCompras = srvCompra.countByUsuario(this.usuarioAuteticado.getUsuario());
		this.numVentas = srvVenta.countByUsuario(this.usuarioAuteticado.getUsuario());
		this.numClientes = srvCliente.countByUsuario(this.usuarioAuteticado.getUsuario());
		if (this.usuarioAuteticado.getUsuario() != null && this.usuarioAuteticado.getUsuario().getEmail() != null) {
			crearDonutFormasPago();
			crearAreaClientes();
			crearAreaVentas();
			crearBarGastosIngresos();
			crearCalendario();
		}
		logger.debug(empresa);
		if (empresa != null)
			addInfo(empresa);

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Map params = externalContext.getRequestParameterMap();
		logger.debug((String) params.get("empresa"));
	}

	private DonutChartModel iniciarDonutFormasPago() {
		DonutChartModel model = new DonutChartModel();

		Map<String, Number> donut = new LinkedHashMap<String, Number>();

		List<GraficaGenericDto> datosGraficaVentas = srvVenta
				.findFormasPagoGrafica(this.usuarioAuteticado.getUsuario().getEmail());
		for (GraficaGenericDto registro : datosGraficaVentas) {
			donut.put(registro.getEtiqueta(), registro.getCantidad());
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

	public BarChartModel iniciarBarGastosIngresos() {
		BarChartModel model = new BarChartModel();

		Double totalIng = 0.0;
		Double totasGastos = 0.0;

		ChartSeries ingresos = new ChartSeries();
		ingresos.setLabel("Ingresos");

		List<GraficaGenericDto> datosGraficaIngresos = srvVenta
				.getIngresosMensualesGrafica(this.usuarioAuteticado.getUsuario().getEmail());

		for (GraficaGenericDto registro : datosGraficaIngresos) {
			ingresos.set(registro.getEtiqueta(), registro.getCantidad());
			totalIng = +(Double) registro.getCantidad();
		}

		ChartSeries gastos = new ChartSeries();
		gastos.setLabel("Gastos");
		List<GraficaGenericDto> datosGraficaGastos = srvCompra
				.getGastosMensualesGrafica(this.usuarioAuteticado.getUsuario().getEmail());

		for (GraficaGenericDto registro : datosGraficaGastos) {
			gastos.set(registro.getEtiqueta(), registro.getCantidad());
			totasGastos = +(Double) registro.getCantidad();
		}
		model.addSeries(ingresos);
		model.addSeries(gastos);
		model.setAnimate(true);
		model.setExtender("customExtender");
		this.beneficio = totalIng - totasGastos;
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

	public void crearAreaClientes() {
		areaClientes = new LineChartModel();

		LineChartSeries clientes = new LineChartSeries();
		clientes.setFill(true);
		clientes.setLabel("Clientes");

		List<GraficaGenericDto> datosGraficaClientes = srvCliente
				.findClientesGrafica(this.usuarioAuteticado.getUsuario().getEmail());
		for (GraficaGenericDto registro : datosGraficaClientes) {
			clientes.set(registro.getEtiqueta(), registro.getCantidad());

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

	public void crearAreaVentas() {
		areaVentas = new LineChartModel();

		LineChartSeries ventas = new LineChartSeries();
		ventas.setFill(true);
		ventas.setLabel("Ventas");

		List<GraficaGenericDto> datosGraficaVentas = srvVenta
				.getVentasMensualesGrafica(this.usuarioAuteticado.getUsuario().getEmail());
		for (GraficaGenericDto registro : datosGraficaVentas) {
			ventas.set(registro.getEtiqueta(), registro.getCantidad());
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

	// CALENDARIO

	private void crearCalendario() {
		eventModel = new DefaultScheduleModel();

		this.eventos = this.srvEvento.findEventosByUsuario(this.usuarioAuteticado.getUsuario());

		for (Evento e : eventos) {
			eventModel.addEvent(new DefaultScheduleEvent(e.getTitulo(), e.getFechaInicio(), e.getFechaFin()));
		}

	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public void addEvent(ActionEvent actionEvent) {
		if (event.getId() == null) {
			eventModel.addEvent(event);
		} else {
			eventModel.updateEvent(event);
		}
		parsearEvento(event);
		event = new DefaultScheduleEvent();
	}

	public void parsearEvento(ScheduleEvent event) {
		Evento evento = new Evento();

		evento.setIdEvento(event.getId());
		evento.setDiaEntero(event.isAllDay());
		evento.setFechaInicio(event.getStartDate());
		evento.setFechaFin(event.getEndDate());
		evento.setTitulo(event.getTitle());
		evento.setUsuario(this.usuarioAuteticado.getUsuario());
		this.srvEvento.saveOrUpdate(evento);

	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
	}

	public void onDateSelect(SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		this.event = event.getScheduleEvent();
		parsearEvento(this.event);
		this.event = new DefaultScheduleEvent();

	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		this.event = event.getScheduleEvent();
		parsearEvento(this.event);
		this.event = new DefaultScheduleEvent();

	}

	public void cargarNovedades() {
		novedades = this.srvNovedad.findNovedadesByUsuario(this.usuarioAuteticado.getUsuario(), LIMIT_NOVEDADES);
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

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
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

}
