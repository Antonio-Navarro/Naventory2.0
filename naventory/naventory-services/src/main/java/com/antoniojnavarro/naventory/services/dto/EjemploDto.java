package com.antoniojnavarro.naventory.services.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.antoniojnavarro.naventory.model.entities.enums.EnumEjemplo;
import com.antoniojnavarro.naventory.services.commons.dto.GenericDto;

// Necesario para su envio a través de servicios web RESTful
@XmlRootElement(name="Ejemplo")
public class EjemploDto implements GenericDto {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank
	@Size(max=250)
	private String nombre;
	@NotNull
	private Date fechaAlta;
	@NotNull
	private EnumEjemplo enumEjemplo;
	@PositiveOrZero
	private Double valor;
	
	private EjemploDto() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void id(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public EjemploDto nombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public EjemploDto fechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
		return this;
	}

	public EnumEjemplo getEnumEjemplo() {
		return enumEjemplo;
	}

	public void setEnumEjemplo(EnumEjemplo enumEjemplo) {
		this.enumEjemplo = enumEjemplo;
	}
	
	public EjemploDto enumEjemplo(EnumEjemplo enumEjemplo) {
		this.enumEjemplo = enumEjemplo;
		return this;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public EjemploDto valor(Double valor) {
		this.valor = valor;
		return this;
	}
	
	public static EjemploDto newInstance() {
		return new EjemploDto();
	}

}
