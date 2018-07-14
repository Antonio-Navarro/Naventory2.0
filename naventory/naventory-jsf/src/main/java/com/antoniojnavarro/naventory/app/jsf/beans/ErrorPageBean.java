package com.antoniojnavarro.naventory.app.jsf.beans;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.util.Constantes;

@Named("errorPageBean")
@Scope(value = PFScope.REQUEST_SCOPED)
public class ErrorPageBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private String stackTraceException;
	private String messageException;

	@PostConstruct
	public void init() {
		Exception ex = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove(Constantes.SESSION_ATTR_EXCEPTION_ASPECT);
		if (ex != null) {
			this.messageException = ex.getMessage();
			
			StringWriter writer = new StringWriter();
			ex.printStackTrace(new PrintWriter(writer));
			this.stackTraceException = writer.toString();
		}
	}

	public String getStackTraceException() {
		return stackTraceException;
	}

	public void setStackTraceException(String stackTraceException) {
		this.stackTraceException = stackTraceException;
	}
	
	public String getMessageException() {
		return messageException;
	}
	
	public void setMessageException(String messageException) {
		this.messageException = messageException;
	}

}
