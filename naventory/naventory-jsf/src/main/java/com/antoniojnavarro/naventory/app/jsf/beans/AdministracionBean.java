package com.antoniojnavarro.naventory.app.jsf.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;

@Named("administracionBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class AdministracionBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(AdministracionBean.class);

	// CAMPOS
	// ENTITIES

	// LISTAS
	private List<Usuario> usuarios;
	// Graficas
	private LineChartModel graficaCrecimiento;
	// SERVICIOS
	@Autowired
	private ServicioUsuario srvUsuario;

	@PostConstruct
	public void init() {

		logger.info("Administracion.init()");
		cargarUsuarios();
		crearGrafica();
	}

	private void crearGrafica() {
		graficaCrecimiento = initGraficaModel();
		graficaCrecimiento.setTitle("Crecimiento de usuarios");
		graficaCrecimiento.setLegendPosition("e");
		graficaCrecimiento.setShowPointLabels(true);
		graficaCrecimiento.getAxes().put(AxisType.X, new CategoryAxis("Años"));
		Axis yAxis = graficaCrecimiento.getAxis(AxisType.Y);
		yAxis.setLabel("Número de usuarios");
		graficaCrecimiento.setAnimate(true);
	}

	private LineChartModel initGraficaModel() {
		LineChartModel model = new LineChartModel();

		ChartSeries users = new ChartSeries();

		users.setLabel("Usuarios");
		Object[] usersDB = this.srvUsuario.findUsersGrafica();
		for (int i = 0; i < usersDB.length; i++) {
			Object[] datos = (Object[]) usersDB[i];
			users.set(datos[0].toString(), (Number) datos[1]);
		}

		model.addSeries(users);
		return model;
	}

	public void inicilizarAtributos() {
		this.usuarios = null;
	}

	public void cargarUsuarios() {
		this.usuarios = srvUsuario.findAll();
	}

	public void cambiarEstadoActivo(Usuario user) {
		user.setActivo(("Y".equals(user.getActivo())) ? "N" : "Y");
		srvUsuario.saveOrUpdate(user, false);
		addInfo("users.succesUpdate");
	}

	public void cambiarEstadoAdmin(Usuario user) {
		user.setAdministrador(("Y".equals(user.getAdministrador())) ? "N" : "Y");
		srvUsuario.saveOrUpdate(user, false);
		addInfo("users.succesUpdate");
	}

	public void borrarUsuario(Usuario user) {

		srvUsuario.delete(user);
		this.usuarios.remove(user);
		addInfo("users.succesDelete");
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public LineChartModel getGraficaCrecimiento() {
		return graficaCrecimiento;
	}

	public void setGraficaCrecimiento(LineChartModel graficaCrecimiento) {
		this.graficaCrecimiento = graficaCrecimiento;
	}

}