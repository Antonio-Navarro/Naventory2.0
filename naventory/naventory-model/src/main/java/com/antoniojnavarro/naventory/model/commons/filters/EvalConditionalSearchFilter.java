package com.antoniojnavarro.naventory.model.commons.filters;

import java.io.Serializable;

@FunctionalInterface
public interface EvalConditionalSearchFilter extends Serializable {

	boolean check(SearchFilter searchFilter);

}
