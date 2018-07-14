package com.antoniojnavarro.naventory.services.dto;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import com.antoniojnavarro.naventory.services.commons.dto.GenericDto;

public class UsuarioDto implements GenericDto {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank
	@Size(max=250)
	private String nombre;
	@NotBlank
	@Size(max=250)
	private String apellido1;
	@NotBlank
	@Size(max=250)
	private String apellido2;
	@NotBlank
	@Size(max=250)
	private String login;
	private Date fechaBaja;

	@NotEmpty
	@Valid
	private List<RoleDto> roles;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String clave;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioDto id(Long id) {
		this.id = id;
		return this;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public UsuarioDto nombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public UsuarioDto apellido1(String apellido1) {
		this.apellido1 = apellido1;
		return this;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public UsuarioDto apellido2(String apellido2) {
		this.apellido2 = apellido2;
		return this;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public UsuarioDto login(String login) {
		this.login = login;
		return this;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public UsuarioDto fechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
		return this;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public UsuarioDto clave(String clave) {
		this.clave = clave;
		return this;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

	public UsuarioDto roles(List<RoleDto> roles) {
		this.roles = roles;
		return this;
	}

}
