package com.antoniojnavarro.naventory.app.jsf.LazyDataModels;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.app.util.SortOrderParseUtil;
import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.model.entities.Cliente;
import com.antoniojnavarro.naventory.model.filters.ClienteSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioCliente;

public class ClienteLazyDataModel extends LazyDataModel<Cliente> {
	private static final long serialVersionUID = 1L;

	// CAMPOS
	private ClienteSearchFilter clienteFilter;

	private int numResults;

	// LISTAS
	private PaginationResult<Cliente> paginationResult;
	// SERVICIOS
	private ServicioCliente srvCliente;

	public ClienteLazyDataModel(ClienteSearchFilter clienteFilter, ServicioCliente srvCliente) {
		super();
		this.clienteFilter = clienteFilter;
		this.srvCliente = srvCliente;
	}

	@Override
	public Cliente getRowData(String rowKey) {
		return srvCliente.findById(Integer.parseInt(rowKey));
	}

	@Override
	public Object getRowKey(Cliente object) {
		return object.getIdCliente().toString();
	}

	@Override
	public List<Cliente> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		paginationResult = srvCliente.findBySearchFilterPagination(clienteFilter,
				pageSize > 0 ? (first / pageSize) + 1 : 1, pageSize > 0 ? pageSize : Constantes.DEFAULT_PAGE_SIZE,
				sortField, SortOrderParseUtil.parseSortOrderPrimefacesToSortOrderDao(sortOrder));
		numResults = (int) paginationResult.getTotalResult();
		this.setRowCount(Long.valueOf(numResults).intValue());
		return paginationResult.getResult();
	}

	public ClienteSearchFilter getClienteFilter() {
		return clienteFilter;
	}

	public void setClienteFilter(ClienteSearchFilter clienteFilter) {
		this.clienteFilter = clienteFilter;
	}

	public int getNumResults() {
		return numResults;
	}

	public void setNumResults(int numResults) {
		this.numResults = numResults;
	}

	public PaginationResult<Cliente> getPaginationResult() {
		return paginationResult;
	}

	public void setPaginationResult(PaginationResult<Cliente> paginationResult) {
		this.paginationResult = paginationResult;
	}

}
