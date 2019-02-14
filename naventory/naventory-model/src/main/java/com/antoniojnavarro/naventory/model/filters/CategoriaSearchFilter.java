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

	@FieldWhere(columns = "c.idCat", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Integer id;

	@FieldWhere(columns = "c.usuario.email", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private String usuario;

	@FieldWhere(columns = "c.nomCat", likeMode = LikeMode.CONTAINS)
	private String nombre;

	@FieldWhere(columns = "c.desc", likeMode = LikeMode.CONTAINS)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		CategoriaSearchFilter other = (CategoriaSearchFilter) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

}
