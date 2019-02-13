package com.antoniojnavarro.naventory.model.filters;

import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.EntityFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.LikeMode;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.OperatorLikeNoneEnum;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.OrderByColumn;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.OrderByMultipleColumns;
import com.antoniojnavarro.naventory.model.entities.Categoria;;

@EntityFilter(entity = Categoria.class, abbr = "c")
@OrderByMultipleColumns({ @OrderByColumn(column = "c.nomCat"), @OrderByColumn(column = "c.idCat") })
public class CategoriaSearchFilter implements SearchFilter {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean isEmpty() {
		return false;
	}

	@FieldWhere(columns = "c.idcat", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Integer id;

	@FieldWhere(columns = "c.usuario.email", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private String usuario;

	@FieldWhere(columns = "c.nomCat", likeMode = LikeMode.CONTAINS)
	private String nombre;

	@FieldWhere(columns = "c.descripcion", likeMode = LikeMode.CONTAINS)
	private String descripcion;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
