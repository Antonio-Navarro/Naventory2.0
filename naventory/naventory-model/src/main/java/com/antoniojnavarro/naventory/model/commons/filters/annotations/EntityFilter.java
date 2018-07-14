package com.antoniojnavarro.naventory.model.commons.filters.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.antoniojnavarro.naventory.model.commons.GenericEntity;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface EntityFilter {

	Class<? extends GenericEntity> entity();

	String abbr() default "e";

}