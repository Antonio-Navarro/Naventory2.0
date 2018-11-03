package com.antoniojnavarro.naventory.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.antoniojnavarro.naventory.model.commons.GenericEntity;

@Entity
@DynamicInsert
@Table(name = "evento")
public class Evento implements GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_evento",length = 255)
	private String idEvento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "email")
	private Usuario usuario;

	@Column(name = "titulo", length = 255, nullable = true)
	private String titulo;

	@Column(name = "fecha_inicio")
	private Date fechaInicio;

	@Column(name = "fecha_fin")
	private Date fechaFin;

	@Column(name = "dia_entero", columnDefinition = "TINYINT(1)")
	private Boolean diaEntero;

	public String getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Boolean getDiaEntero() {
		return diaEntero;
	}

	public void setDiaEntero(Boolean diaEntero) {
		this.diaEntero = diaEntero;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diaEntero == null) ? 0 : diaEntero.hashCode());
		result = prime * result + ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + ((idEvento == null) ? 0 : idEvento.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Evento other = (Evento) obj;
		if (diaEntero == null) {
			if (other.diaEntero != null)
				return false;
		} else if (!diaEntero.equals(other.diaEntero))
			return false;
		if (fechaFin == null) {
			if (other.fechaFin != null)
				return false;
		} else if (!fechaFin.equals(other.fechaFin))
			return false;
		if (fechaInicio == null) {
			if (other.fechaInicio != null)
				return false;
		} else if (!fechaInicio.equals(other.fechaInicio))
			return false;
		if (idEvento == null) {
			if (other.idEvento != null)
				return false;
		} else if (!idEvento.equals(other.idEvento))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

}
