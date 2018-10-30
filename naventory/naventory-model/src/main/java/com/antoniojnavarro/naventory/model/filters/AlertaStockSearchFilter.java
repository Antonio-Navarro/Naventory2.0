package com.antoniojnavarro.naventory.model.filters;

import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.EntityFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.OperatorLikeNoneEnum;
import com.antoniojnavarro.naventory.model.entities.AlertaStock;;

@EntityFilter(entity = AlertaStock.class, abbr = "c")
public class AlertaStockSearchFilter implements SearchFilter {

	private static final long serialVersionUID = 1L;

	
	@FieldWhere(columns = "c.sku", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private String sku;

	public AlertaStockSearchFilter sku(String sku) {
		this.sku = sku;
		return this;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (sku == null || sku.isEmpty());
	}


	
	

}
