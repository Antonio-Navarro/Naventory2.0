package com.antoniojnavarro.naventory.app.jsf.beans;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
@Named("paramBean")
@Scope(value = PFScope.SESSION_SCOPED)
public class ParamBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Object param;

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

}
