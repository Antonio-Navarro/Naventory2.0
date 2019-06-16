package com.antoniojnavarro.naventory.app.jsf.LazyDataModels;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.app.util.SortOrderParseUtil;
import com.antoniojnavarro.naventory.model.entities.Proveedor;
import com.antoniojnavarro.naventory.model.filters.ProveedorSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.services.api.ServicioProveedor;

public class ProveedorLazyDataModel extends LazyDataModel<Proveedor> {
	private static final long serialVersionUID = 1L;

	// CAMPOS
	private ProveedorSearchFilter proveedorFilter;

	private int numResults;

	// LISTAS
	private PaginationResult<Proveedor> paginationResult;
	// SERVICIOS
	private ServicioProveedor srvProveedor;

	public ProveedorLazyDataModel(ProveedorSearchFilter proveedorFilter, ServicioProveedor srvProveedor) {
		super();
		this.proveedorFilter = proveedorFilter;
		this.srvProveedor = srvProveedor;
	}

	@Override
	public Proveedor getRowData(String rowKey) {
		return srvProveedor.findById(Integer.parseInt(rowKey));
	}

	@Override
	public Object getRowKey(Proveedor object) {
		return object.getIdProv().toString();
	}

	@Override
	public List<Proveedor> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		paginationResult = srvProveedor.findBySearchFilterPagination(proveedorFilter,
				pageSize > 0 ? (first / pageSize) + 1 : 1, pageSize > 0 ? pageSize : Constantes.DEFAULT_PAGE_SIZE,
				sortField, SortOrderParseUtil.parseSortOrderPrimefacesToSortOrderDao(sortOrder));
		numResults = (int) paginationResult.getTotalResult();
		this.setRowCount(Long.valueOf(numResults).intValue());
		return paginationResult.getResult();
	}

	public ProveedorSearchFilter getProveedorFilter() {
		return proveedorFilter;
	}

	public void setProveedorFilter(ProveedorSearchFilter proveedorFilter) {
		this.proveedorFilter = proveedorFilter;
	}

	public int getNumResults() {
		return numResults;
	}

	public void setNumResults(int numResults) {
		this.numResults = numResults;
	}

	public PaginationResult<Proveedor> getPaginationResult() {
		return paginationResult;
	}

	public void setPaginationResult(PaginationResult<Proveedor> paginationResult) {
		this.paginationResult = paginationResult;
	}

}
