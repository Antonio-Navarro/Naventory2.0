package com.antoniojnavarro.naventory.model.filters;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.BetweenDate;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.BetweenDate.ModeEnum;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.EntityFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.LikeMode;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.OrderByColumn;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.OrderByMultipleColumns;
import com.antoniojnavarro.naventory.model.entities.Usuario;

@EntityFilter(entity = Usuario.class, abbr = "u")
@OrderByMultipleColumns({ @OrderByColumn(column = "u.apellido"), @OrderByColumn(column = "u.nombre"),
		@OrderByColumn(column = "u.email") })
public class UsuarioSearchFilter implements SearchFilter {

	private static final long serialVersionUID = 1L;

	@FieldWhere(columns = "u.nombre", likeMode = LikeMode.CONTAINS)
	private String nombre;

	@FieldWhere(columns = "u.fechaAlta")
	@BetweenDate(mode = ModeEnum.AFTER_EQUALS)
	private Date fechaAltaDesde;

	@FieldWhere(columns = "u.fechaAlta")
	@BetweenDate(mode = ModeEnum.BEFORE_EQUALS)
	private Date fechaAltaHasta;

	public String getNombre() {
		return nombre;
	}

	public UsuarioSearchFilter nombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaAltaDesde() {
		return fechaAltaDesde;
	}

	public UsuarioSearchFilter fechaAltaDesde(Date fechaAltaDesde) {
		this.fechaAltaDesde = fechaAltaDesde;
		return this;
	}

	public void setFechaAltaDesde(Date fechaAltaDesde) {
		this.fechaAltaDesde = fechaAltaDesde;
	}

	public Date getFechaAltaHasta() {
		return fechaAltaHasta;
	}

	public UsuarioSearchFilter fechaAltaHasta(Date fechaAltaHasta) {
		this.fechaAltaHasta = fechaAltaHasta;
		return this;
	}

	public void setFechaAltaHasta(Date fechaAltaHasta) {
		this.fechaAltaHasta = fechaAltaHasta;
	}

	@Override
	public boolean isEmpty() {
		return StringUtils.isEmpty(nombre) && fechaAltaDesde == null && fechaAltaHasta == null;
	}
}
