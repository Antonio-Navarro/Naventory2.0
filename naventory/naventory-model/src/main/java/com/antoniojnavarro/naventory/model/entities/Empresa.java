package com.antoniojnavarro.naventory.model.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.antoniojnavarro.naventory.model.commons.GenericEntity;

@Entity
@DynamicInsert
@Table(name = "empresa")
public class Empresa implements GenericEntity {

	private static final long serialVersionUID = 1L;

	public Empresa() {
	}

	public Empresa(String cif) {
		this.cif = cif;
	}

	@Id
	@Column(name = "cif", length = 150, nullable = false)
	private String cif;

	@Column(name = "nombre", length = 255, nullable = false)
	private String nombre;

	@Column(name = "domicilio_social", length = 255, nullable = false)
	private String domicilioSocial;

	@Column(name = "telefono", length = 8)
	private Integer telefono;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "empresa")
	private List<Usuario> usuarios;

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilioSocial() {
		return domicilioSocial;
	}

	public void setDomicilioSocial(String domicilioSocial) {
		this.domicilioSocial = domicilioSocial;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
