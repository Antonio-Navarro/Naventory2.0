package com.antoniojnavarro.naventory.app.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.antoniojnavarro.naventory.model.entities.Cliente;
import com.antoniojnavarro.naventory.services.api.ServicioCliente;

@Component("clienteConverter")
public class ClienteConverter implements Converter {

	@Autowired
	private ServicioCliente service;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				return service.findById(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return String.valueOf(((Cliente) value).getIdCliente());
		} else {
			return "";
		}
	}

}
