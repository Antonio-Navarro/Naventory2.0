package com.antoniojnavarro.naventory.model.filters;

import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.EntityFilter;
import com.antoniojnavarro.naventory.model.entities.Producto;;

@EntityFilter(entity = Producto.class, abbr = "c")
public class ProductoSearchFilter implements SearchFilter {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean isEmpty() {
		return false;
	}
}
