package com.antoniojnavarro.naventory.model.filters;

import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.EntityFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.LikeMode;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.OperatorLikeNoneEnum;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.OrderByColumn;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.OrderByMultipleColumns;
import com.antoniojnavarro.naventory.model.entities.Empresa;

@EntityFilter(entity = Empresa.class, abbr = "u")
@OrderByMultipleColumns(@OrderByColumn(column = "u.cif"))
public class EmpresaSearchFilter implements SearchFilter {

	private static final long serialVersionUID = 1L;

	@FieldWhere(columns = "u.nombre", likeMode = LikeMode.CONTAINS)
	private String nombre;

	@FieldWhere(columns = "u.cif", likeMode = LikeMode.CONTAINS)
	private String cif;

	@FieldWhere(columns = "u.domicilioSocial", likeMode = LikeMode.CONTAINS)
	private String domicilioSocial;

	@FieldWhere(columns = "u.telefono", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Integer telefono;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
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

	@Override
	public boolean isEmpty() {
		return false;
	}

}
