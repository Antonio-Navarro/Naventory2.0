package com.antoniojnavarro.naventory.model.filters;

import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.EntityFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.OperatorLikeNoneEnum;
import com.antoniojnavarro.naventory.model.entities.Compra;;

@EntityFilter(entity = Compra.class, abbr = "c")
public class CompraSearchFilter implements SearchFilter {

	private static final long serialVersionUID = 1L;
	@FieldWhere(columns = "c.factura", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private String factura;
	@FieldWhere(columns = "c.usuario.email", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private String usuario;

	public CompraSearchFilter factura(String factura) {
		this.factura = factura;
		return this;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	public CompraSearchFilter usuario(String email) {
		// TODO Auto-generated method stub
		this.usuario = email;
		return this;
	}
}
