package com.antoniojnavarro.naventory.model.filters;

import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.EntityFilter;
import com.antoniojnavarro.naventory.model.entities.Novedad;;

@EntityFilter(entity = Novedad.class, abbr = "c")
public class EventoSearchFilter implements SearchFilter {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
