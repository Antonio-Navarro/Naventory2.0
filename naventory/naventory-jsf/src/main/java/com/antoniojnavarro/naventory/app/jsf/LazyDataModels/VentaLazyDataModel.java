package com.antoniojnavarro.naventory.app.jsf.LazyDataModels;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.app.util.SortOrderParseUtil;
import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.model.entities.Venta;
import com.antoniojnavarro.naventory.model.filters.VentaSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioVenta;

public class VentaLazyDataModel extends LazyDataModel<Venta> {
	private static final long serialVersionUID = 1L;

	// CAMPOS
	private VentaSearchFilter ventaFilter;

	private int numResults;

	// LISTAS
	private PaginationResult<Venta> paginationResult;
	// SERVICIOS
	private ServicioVenta srvVenta;

	public VentaLazyDataModel(VentaSearchFilter ventaFilter, ServicioVenta srvVenta) {
		super();
		this.ventaFilter = ventaFilter;
		this.srvVenta = srvVenta;
	}

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

//		ventaFilter.setNombreProducto((String) filters.get("nombre"));
//		ventaFilter.setNombreCliente((String) filters.get("cliente.nombre"));
//
//		ventaFilter.setCantidad(
//				(String) filters.get("cantidad") != null ? Integer.parseInt((String) filters.get("cantidad")) : null);
//		try {
//			ventaFilter
//					.setFecha((String) filters.get("fecha") != null ? sdf.parse((String) filters.get("fecha")) : null);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		paginationResult = srvVenta.findBySearchFilterPagination(ventaFilter, pageSize > 0 ? (first / pageSize) + 1 : 1,
				pageSize > 0 ? pageSize : Constantes.DEFAULT_PAGE_SIZE, sortField,
				SortOrderParseUtil.parseSortOrderPrimefacesToSortOrderDao(sortOrder));
		numResults = (int) paginationResult.getTotalResult();
		this.setRowCount(Long.valueOf(numResults).intValue());
		return paginationResult.getResult();
	}

	public VentaSearchFilter getVentaFilter() {
		return ventaFilter;
	}

	public void setVentaFilter(VentaSearchFilter ventaFilter) {
		this.ventaFilter = ventaFilter;
	}

	public int getNumResults() {
		return numResults;
	}

	public void setNumResults(int numResults) {
		this.numResults = numResults;
	}

	public PaginationResult<Venta> getPaginationResult() {
		return paginationResult;
	}

	public void setPaginationResult(PaginationResult<Venta> paginationResult) {
		this.paginationResult = paginationResult;
	}

	public ServicioVenta getSrvVenta() {
		return srvVenta;
	}

	public void setSrvVenta(ServicioVenta srvVenta) {
		this.srvVenta = srvVenta;
	}

}
