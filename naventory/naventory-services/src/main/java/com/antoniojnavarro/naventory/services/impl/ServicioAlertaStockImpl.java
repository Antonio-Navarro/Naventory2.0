package com.antoniojnavarro.naventory.services.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.dao.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.dao.repositories.AlertaStockDao;
import com.antoniojnavarro.naventory.model.entities.AlertaStock;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.AlertaStockSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioAlertaStock;
import com.antoniojnavarro.naventory.services.api.ServicioEmpresa;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMail;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioAlertaStockImpl implements ServicioAlertaStock {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;

	@Autowired
	private ServicioMensajesI18n srvMensajes;
	@Autowired
	ServicioMail srvMail;
	@Autowired
	private AlertaStockDao alertaStockDao;
	@Autowired
	private ServicioEmpresa srvEmpresa;

	@Override
	public AlertaStock findById(Integer id) throws ServicioException {
		return this.alertaStockDao.findOne(id);
	}

	@Override
	public List<AlertaStock> findBySearchFilter(AlertaStockSearchFilter searchFilter) throws ServicioException {
		return alertaStockDao.findBySearchFilter(searchFilter);
	}

	@Override
	public List<AlertaStock> findBySearchFilter(AlertaStockSearchFilter searchFilter, String sortField,
			SortOrderEnum sortOrder) throws ServicioException {
		return this.alertaStockDao.findBySearchFilter(searchFilter, sortField, sortOrder);
	}

	@Override
	public PaginationResult<AlertaStock> findBySearchFilterPagination(AlertaStockSearchFilter searchFilter,
			int pageNumber, int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.alertaStockDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField,
				sortOrder);
	}

	@Override
	public PaginationResult<AlertaStock> findBySearchFilterPagination(AlertaStockSearchFilter searchFilter,
			int pageNumber, int pageSize) throws ServicioException {
		return this.alertaStockDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}

	@Override
	public List<AlertaStock> findAll() throws ServicioException {
		return (List<AlertaStock>) this.alertaStockDao.findAll();
	}

	@Override
	public boolean exists(AlertaStock entity) throws ServicioException {
		if (entity == null) {
			throw new IllegalArgumentException("Entity no puede ser un nulo");
		}
		if (entity.getIdAlerta() == null)
			return false;
		return existsById(entity.getIdAlerta());
	}

	@Override
	public boolean existsById(Integer id) throws ServicioException {
		return this.existsById(id);
	}

	@Override
	public AlertaStock save(AlertaStock entity) throws ServicioException {
		return this.alertaStockDao.save(entity);
	}

	@Override
	public void validate(AlertaStock entity) throws ServicioException {
		this.srvValidacion.isNull("AlertaStock", entity);
		if (!this.srvEmpresa.existsEmpresaByCif(entity.getEmpresa().getCif())) {
			throw new ServicioException(srvMensajes.getMensajeI18n("cif.noexist"));

		}
	}

	@Override
	public AlertaStock saveOrUpdate(AlertaStock entity, boolean validate) throws ServicioException {
		if (validate) {
			validate(entity);
		}
		return this.save(entity);
	}

	@Override
	public void delete(AlertaStock entity) throws ServicioException {

		this.alertaStockDao.delete(entity);
	}

	@Override
	public void deleteRange(List<AlertaStock> entity) throws ServicioException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Integer id) throws ServicioException {
		this.alertaStockDao.delete(id);

	}

	@Override
	public List<AlertaStock> findAlertasByEmpresa(Empresa empresa) throws ServicioException {
		return this.alertaStockDao.findAlertasByEmpresa(empresa);
	}

	@Override
	public String comprobarAlerta(Producto entity) throws ServicioException {
		AlertaStock a = this.alertaStockDao.findAlertaByEmpresaAndProducto(entity.getEmpresa(), entity);
		if (entity.getStock() <= entity.getStockMin()) {
			if (a == null) {
				a = new AlertaStock();
				a.setProducto(entity);
				a.setEmpresa(entity.getEmpresa());
				this.save(a);
			}
			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.submit(() -> {
				String body = "";
				body = "<center><img src='http://naventory.cerrajerianavarro.es/assets/img/logofinal.jpg'></center><hr/><br> Empresa  "
						+ entity.getEmpresa().getNombre() + "<br>";
				body += "<h3 style='color:red'>Producto con nivel de stock bajo </h3> <br><br>";
				body += "Quedan <b>" + entity.getStock() + "</b> " + entity.getUnidad() + " del producto <b>"
						+ entity.getNombre() + "</b><br><br>";
				this.srvMail.sendEmail(entity.getEmpresa().getUsuarios().stream().map(Usuario::getEmail).collect(Collectors.toList()), "Alerta de inventario", body);

			});
			return "venta.stockBajo";
		} else if (a != null && entity.getStock() > entity.getStockMin()) {
			this.alertaStockDao.delete(a);
		}
		return null;
	}

	@Override
	public void comprobarAlertaRecepcionProducto(Producto entity) throws ServicioException {
		AlertaStock a = this.alertaStockDao.findAlertaByEmpresaAndProducto(entity.getEmpresa(), entity);
		if (entity.getStock() > entity.getStockMin() && a != null) {
			this.alertaStockDao.delete(a);
		}
	}

	@Override
	public AlertaStock findAlertaByEmpresaAndProducto(Empresa empresa, Producto product) throws ServicioException {
		return this.alertaStockDao.findAlertaByEmpresaAndProducto(empresa, product);
	}
}
