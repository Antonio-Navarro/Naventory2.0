package com.antoniojnavarro.naventory.model.entities;

import javax.persistence.CascadeType;
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
@Table(name = "categoria")
public class Categoria implements GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcat")
	private Integer idCat;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "cif")
	private Empresa empresa;

	@Column(name = "nomcat", length = 255, nullable = false)
	private String nomCat;

	@Column(name = "descripcion", length = 255, nullable = true)
	private String desc;

	@Column(name = "obser", length = 255, nullable = true)
	private String obser;

	public Integer getIdCat() {
		return idCat;
	}

	public void setIdCat(Integer idCat) {
		this.idCat = idCat;
	}

	public String getNomCat() {
		return nomCat;
	}

	public void setNomCat(String nomCat) {
		this.nomCat = nomCat;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getObser() {
		return obser;
	}

	public void setObser(String obser) {
		this.obser = obser;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((idCat == null) ? 0 : idCat.hashCode());
		result = prime * result + ((nomCat == null) ? 0 : nomCat.hashCode());
		result = prime * result + ((obser == null) ? 0 : obser.hashCode());
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
		Categoria other = (Categoria) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (idCat == null) {
			if (other.idCat != null)
				return false;
		} else if (!idCat.equals(other.idCat))
			return false;
		if (nomCat == null) {
			if (other.nomCat != null)
				return false;
		} else if (!nomCat.equals(other.nomCat))
			return false;
		if (obser == null) {
			if (other.obser != null)
				return false;
		} else if (!obser.equals(other.obser))
			return false;
		return true;
	}

}
