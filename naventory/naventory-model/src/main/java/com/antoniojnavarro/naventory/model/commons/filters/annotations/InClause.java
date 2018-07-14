package com.antoniojnavarro.naventory.model.commons.filters.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.antoniojnavarro.naventory.model.commons.filters.DefaultEvalConditionalSearchFilter;
import com.antoniojnavarro.naventory.model.commons.filters.EvalConditionalSearchFilter;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InClause {

	String[] columns();
	
	String inSubJQPLQuery();
	
	Class<? extends EvalConditionalSearchFilter> condition() default DefaultEvalConditionalSearchFilter.class;
	
}
