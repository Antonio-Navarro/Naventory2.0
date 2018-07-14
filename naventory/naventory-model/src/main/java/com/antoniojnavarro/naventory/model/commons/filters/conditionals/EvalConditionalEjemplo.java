package com.antoniojnavarro.naventory.model.commons.filters.conditionals;

import com.antoniojnavarro.naventory.model.commons.filters.EvalConditionalSearchFilter;
import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;
import com.antoniojnavarro.naventory.model.filters.EjemploSearchFilter;

public class EvalConditionalEjemplo implements EvalConditionalSearchFilter {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean check(SearchFilter searchFilter) {
		if (searchFilter instanceof EjemploSearchFilter) {
			EjemploSearchFilter other = (EjemploSearchFilter) searchFilter;
			return other != null && other.getNombre() != null && !other.getNombre().isEmpty();
		}
		return false;
	}

}
