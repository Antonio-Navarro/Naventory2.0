package com.antoniojnavarro.naventory.app.jsf.LazyDataModels;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.app.util.SortOrderParseUtil;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.filters.ProductoSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.services.api.ServicioProducto;

public class ProductoLazyDataModel extends LazyDataModel<Producto> {
	private static final long serialVersionUID = 1L;

	// CAMPOS
	private ProductoSearchFilter productoFilter;

	private int numResults;
	private Boolean stockBajo;
	// LISTAS
	private PaginationResult<Producto> paginationResult;
	// SERVICIOS
	private ServicioProducto srvProducto;

	public ProductoLazyDataModel(ProductoSearchFilter productoFilter, ServicioProducto srvProducto) {
		super();
		this.productoFilter = productoFilter;
		this.srvProducto = srvProducto;
		this.stockBajo = false;
	}

	public ProductoLazyDataModel(ProductoSearchFilter productoFilter, ServicioProducto srvProducto, Boolean stockBajo) {
		super();
		this.productoFilter = productoFilter;
		this.srvProducto = srvProducto;
		this.stockBajo = stockBajo;
	}

	@Override
	public Producto getRowData(String rowKey) {
		return srvProducto.findById(rowKey);
	}

	@Override
	public Object getRowKey(Producto object) {
		return object.getSku();
	}

	@Override
	public List<Producto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		if (!stockBajo) {
			paginationResult = srvProducto.findBySearchFilterPagination(productoFilter,
					pageSize > 0 ? (first / pageSize) + 1 : 1, pageSize > 0 ? pageSize : Constantes.DEFAULT_PAGE_SIZE,
					sortField, SortOrderParseUtil.parseSortOrderPrimefacesToSortOrderDao(sortOrder));
		} else {
			List<Producto> productosStockBajo = srvProducto.findProductosStockBajo(productoFilter.getEmpresa());
			paginationResult = new PaginationResult<Producto>();
			paginationResult.result(productosStockBajo);
			paginationResult.setTotalResult(productosStockBajo.size());
		}
		numResults = (int) paginationResult.getTotalResult();

		this.setRowCount(Long.valueOf(numResults).intValue());

		return paginationResult.getResult();
	}

	public ProductoSearchFilter getProductoFilter() {
		return productoFilter;
	}

	public void setProductoFilter(ProductoSearchFilter productoFilter) {
		this.productoFilter = productoFilter;
	}

	public int getNumResults() {
		return numResults;
	}

	public void setNumResults(int numResults) {
		this.numResults = numResults;
	}

	public PaginationResult<Producto> getPaginationResult() {
		return paginationResult;
	}

	public void setPaginationResult(PaginationResult<Producto> paginationResult) {
		this.paginationResult = paginationResult;
	}

	public Boolean getStockBajo() {
		return stockBajo;
	}

	public void setStockBajo(Boolean stockBajo) {
		this.stockBajo = stockBajo;
	}

}
