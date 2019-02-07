package com.antoniojnavarro.naventory.app.jsf.LazyDataModels;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.app.util.SortOrderParseUtil;
import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.model.entities.Categoria;
import com.antoniojnavarro.naventory.model.filters.CategoriaSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioCategoria;

public class CategoriaLazyDataModel extends LazyDataModel<Categoria> {
	private static final long serialVersionUID = 1L;

	// CAMPOS
	private CategoriaSearchFilter categoriaFilter;

	private int numResults;

	// LISTAS
	private PaginationResult<Categoria> paginationResult;
	// SERVICIOS
	private ServicioCategoria srvCategoria;

	public CategoriaLazyDataModel(CategoriaSearchFilter ventaFilter, ServicioCategoria srvCategoria) {
		super();
		this.categoriaFilter = categoriaFilter;
		this.srvCategoria = srvCategoria;
	}

	@Override
	public Categoria getRowData(String rowKey) {
		return srvCategoria.findById(Integer.parseInt(rowKey));
	}

	@Override
	public Object getRowKey(Categoria object) {
		return object.getIdCat().toString();
	}

	@Override
	public List<Categoria> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		paginationResult = srvCategoria.findBySearchFilterPagination(categoriaFilter,
				pageSize > 0 ? (first / pageSize) + 1 : 1, pageSize > 0 ? pageSize : Constantes.DEFAULT_PAGE_SIZE,
				sortField, SortOrderParseUtil.parseSortOrderPrimefacesToSortOrderDao(sortOrder));
		numResults = (int) paginationResult.getTotalResult();
		this.setRowCount(Long.valueOf(numResults).intValue());
		return paginationResult.getResult();
	}

	public int getNumResults() {
		return numResults;
	}

	public void setNumResults(int numResults) {
		this.numResults = numResults;
	}

	public PaginationResult<Categoria> getPaginationResult() {
		return paginationResult;
	}

	public void setPaginationResult(PaginationResult<Categoria> paginationResult) {
		this.paginationResult = paginationResult;
	}

	public ServicioCategoria getSrvCategoria() {
		return srvCategoria;
	}

	public void setSrvCategoria(ServicioCategoria srvCategoria) {
		this.srvCategoria = srvCategoria;
	}

}
