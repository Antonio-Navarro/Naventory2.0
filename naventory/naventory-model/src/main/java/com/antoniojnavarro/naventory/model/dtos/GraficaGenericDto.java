package com.antoniojnavarro.naventory.model.dtos;

public class GraficaGenericDto {

	private String etiqueta;// bien sea una fecha o un nombre

	private Number cantidad;

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public Number getCantidad() {
		return cantidad;
	}

	public void setCantidad(Number cantidad) {
		this.cantidad = cantidad;
	}

	public GraficaGenericDto(String etiqueta, Number cantidad) {
		super();
		this.etiqueta = etiqueta;
		this.cantidad = cantidad;
	}

}
