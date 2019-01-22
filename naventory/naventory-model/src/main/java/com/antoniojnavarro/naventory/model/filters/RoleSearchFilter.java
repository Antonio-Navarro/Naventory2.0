package com.antoniojnavarro.naventory.model.filters;

import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.EntityFilter;
import com.antoniojnavarro.naventory.model.entities.Role;;

@EntityFilter(entity = Role.class, abbr = "c")
public class RoleSearchFilter implements SearchFilter {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean isEmpty() {
		return false;
	}
}
