package com.antoniojnavarro.naventory.app.jsf.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

public class MasterBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ResourceBundle resourceBundle;

	public void addFatalError(String propertyNameMessage, String... parameters) {
		addMensaje(FacesMessage.SEVERITY_FATAL, propertyNameMessage, parameters);
	}

	public void addFatalError(String propertyNameMessage) {
		addMensaje(FacesMessage.SEVERITY_FATAL, propertyNameMessage);
	}

	public void addError(String propertyNameMessage, String... parameters) {
		addMensaje(FacesMessage.SEVERITY_ERROR, propertyNameMessage, parameters);
	}

	public void addError(String propertyNameMessage) {
		addMensaje(FacesMessage.SEVERITY_ERROR, propertyNameMessage);
	}

	public void addInfo(String propertyNameMessage, String... parameters) {
		addMensaje(FacesMessage.SEVERITY_INFO, propertyNameMessage, parameters);
	}

	public void addInfo(String propertyNameMessage) {
		addMensaje(FacesMessage.SEVERITY_INFO, propertyNameMessage);
	}

	public void addWarning(String propertyNameMessage, String... parameters) {
		addMensaje(FacesMessage.SEVERITY_WARN, propertyNameMessage, parameters);
	}

	public void addWarning(String propertyNameMessage) {
		addMensaje(FacesMessage.SEVERITY_WARN, propertyNameMessage);
	}

	public void addMensaje(FacesMessage.Severity severity, String propertyNameMessage) {
		addMensaje(severity, propertyNameMessage, new String[] {});
	}

	public void addMensaje(FacesMessage.Severity severity, String propertyNameMessage, String... parameters) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		String message = translateI18NProperty(propertyNameMessage, parameters);
		facesContext.addMessage(null, new FacesMessage(severity, message, message));
	}

	public String translateI18NProperty(String propertyName, String... parameters) {
		if (resourceBundle == null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			this.resourceBundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");
		}
		String value = this.resourceBundle.getString(propertyName);
		if (parameters != null && parameters.length > 0) {
			for (int i = 0; i < parameters.length; ++i) {
				value = value.replace("{" + i + "}", parameters[i]);
			}
		}
		return value;
	}

	/**
	 * Abre una ventana de dialog en el cliente.
	 * 
	 * @param dialog
	 */
	public void openDialog(String dialog) {
		PrimeFaces.current().executeScript("PF('" + dialog + "').show();");
	}

	/**
	 * Cierra una ventana de dialog en el cliente.
	 * 
	 * @param dialog
	 */
	public void closeDialog(String dialog) {
		PrimeFaces.current().executeScript("PF('" + dialog + "').hide();");
	}

	/**
	 * Permite realizar un update a un componente de una vista xhtml
	 * 
	 * @param component
	 */
	public void updateComponent(String... component) {
		PrimeFaces.current().ajax().update(Arrays.asList(component));
	}

}
