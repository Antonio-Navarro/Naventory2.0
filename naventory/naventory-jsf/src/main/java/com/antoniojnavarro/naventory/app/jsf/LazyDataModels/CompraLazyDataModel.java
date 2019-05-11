package com.antoniojnavarro.naventory.app.jsf.LazyDataModels;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.app.util.SortOrderParseUtil;
import com.antoniojnavarro.naventory.model.entities.Compra;
import com.antoniojnavarro.naventory.model.filters.CompraSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.services.api.ServicioCompra;

public class CompraLazyDataModel extends LazyDataModel<Compra> {
	private static final long serialVersionUID = 1L;

	// CAMPOS
	private CompraSearchFilter compraFilter;

	private int numResults;

	// LISTAS
	private PaginationResult<Compra> paginationResult;
	// SERVICIOS
	private ServicioCompra srvCompra;

	public CompraLazyDataModel(CompraSearchFilter compraFilter, ServicioCompra srvCompra) {
		super();
		this.compraFilter = compraFilter;
		this.srvCompra = srvCompra;
	}

	@Override
	public Compra getRowData(String rowKey) {
		return srvCompra.findById(Integer.parseInt(rowKey));
	}

	@Override
	public Object getRowKey(Compra object) {
		return object.getIdCompra().toString();
	}

	@Override
	public List<Compra> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		paginationResult = srvCompra.findBySearchFilterPagination(compraFilter,
				pageSize > 0 ? (first / pageSize) + 1 : 1, pageSize > 0 ? pageSize : Constantes.DEFAULT_PAGE_SIZE,
				sortField, SortOrderParseUtil.parseSortOrderPrimefacesToSortOrderDao(sortOrder));
		numResults = (int) paginationResult.getTotalResult();
		this.setRowCount(Long.valueOf(numResults).intValue());
		return paginationResult.getResult();
	}

	public CompraSearchFilter getCompraFilter() {
		return compraFilter;
	}

	public void setCompraFilter(CompraSearchFilter compraFilter) {
		this.compraFilter = compraFilter;
	}

	public int getNumResults() {
		return numResults;
	}

	public void setNumResults(int numResults) {
		this.numResults = numResults;
	}

	public PaginationResult<Compra> getPaginationResult() {
		return paginationResult;
	}

	public void setPaginationResult(PaginationResult<Compra> paginationResult) {
		this.paginationResult = paginationResult;
	}

	public ServicioCompra getSrvCompra() {
		return srvCompra;
	}

	public void setSrvCompra(ServicioCompra srvCompra) {
		this.srvCompra = srvCompra;
	}

}
