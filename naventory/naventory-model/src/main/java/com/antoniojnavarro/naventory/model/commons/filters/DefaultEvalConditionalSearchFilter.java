package com.antoniojnavarro.naventory.model.commons.filters;

public class DefaultEvalConditionalSearchFilter implements EvalConditionalSearchFilter {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean check(SearchFilter searchFilter) {
		return true;
	}

}
