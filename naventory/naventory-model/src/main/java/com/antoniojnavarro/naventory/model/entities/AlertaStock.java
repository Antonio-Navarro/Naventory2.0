package com.antoniojnavarro.naventory.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.antoniojnavarro.naventory.model.commons.GenericEntity;

@Entity
@DynamicInsert
@Table(name = "alerta_stock")
public class AlertaStock implements GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_alerta")
	private Integer idAlerta;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "email")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sku")
	private Producto producto;
	
	public Integer getIdAlerta() {
		return idAlerta;
	}

	public void setIdAlerta(Integer idAlerta) {
		this.idAlerta = idAlerta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAlerta == null) ? 0 : idAlerta.hashCode());
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
		AlertaStock other = (AlertaStock) obj;
		if (idAlerta == null) {
			if (other.idAlerta != null)
				return false;
		} else if (!idAlerta.equals(other.idAlerta))
			return false;
		return true;
	}
	
}
