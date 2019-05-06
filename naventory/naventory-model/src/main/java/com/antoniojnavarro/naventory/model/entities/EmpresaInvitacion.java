package com.antoniojnavarro.naventory.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.antoniojnavarro.naventory.model.commons.GenericEntity;

@Entity
@DynamicInsert
@Table(name = "empresa_invitacion")
public class EmpresaInvitacion implements GenericEntity {

	private static final long serialVersionUID = 1L;

	public EmpresaInvitacion() {
	}

	public EmpresaInvitacion(String cif) {
		this.cif = cif;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_inv")
	private Integer idInvitacion;

	@Column(name = "cif", length = 150, nullable = false)
	private String cif;

	@Column(name = "email", length = 45, nullable = false)
	private String email;
	@Column(name = "token", length = 45, nullable = false)
	private String token;

	@Column(name = "valido", length = 1, nullable = true)
	private String valido;

	public Integer getIdInvitacion() {
		return idInvitacion;
	}

	public void setIdInvitacion(Integer idInvitacion) {
		this.idInvitacion = idInvitacion;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getValido() {
		return valido;
	}

	public void setValido(String valido) {
		this.valido = valido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idInvitacion == null) ? 0 : idInvitacion.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((valido == null) ? 0 : valido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpresaInvitacion other = (EmpresaInvitacion) obj;
		if (cif == null) {
			if (other.cif != null)
				return false;
		} else if (!cif.equals(other.cif))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idInvitacion == null) {
			if (other.idInvitacion != null)
				return false;
		} else if (!idInvitacion.equals(other.idInvitacion))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (valido == null) {
			if (other.valido != null)
				return false;
		} else if (!valido.equals(other.valido))
			return false;
		return true;
	}
	
	

}
