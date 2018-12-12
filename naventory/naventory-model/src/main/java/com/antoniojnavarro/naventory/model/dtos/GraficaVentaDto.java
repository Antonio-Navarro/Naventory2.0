package com.antoniojnavarro.naventory.model.dtos;

public class GraficaVentaDto {

	private String fecha;

	private Long cantidad;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public GraficaVentaDto(String fecha, Long cantidad) {
		super();
		this.fecha = fecha;
		this.cantidad = cantidad;
	}



}
