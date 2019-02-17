package com.antoniojnavarro.naventory.model.filters;

import java.util.Date;

import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.BetweenDate;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.BetweenDate.ModeEnum;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.EntityFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.LikeMode;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.LogicalOperatorBetweenNames;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.OperatorLikeNoneEnum;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.OrderByColumn;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.OrderByMultipleColumns;
import com.antoniojnavarro.naventory.model.entities.Venta;;

@EntityFilter(entity = Venta.class, abbr = "c")
@OrderByMultipleColumns({ @OrderByColumn(column = "c.fecha"), @OrderByColumn(column = "c.cantidad") })
public class VentaSearchFilter implements SearchFilter {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean isEmpty() {
		return false;
	}

	@FieldWhere(columns = "c.cantidad", likeMode = LikeMode.NONE)
	private Integer cantidad;

	@FieldWhere(columns = "c.usuario.email", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS, logicalOperator=LogicalOperatorBetweenNames.AND)
	private String usuario;

	@FieldWhere(columns = "c.producto.nombre", likeMode = LikeMode.CONTAINS)
	private String nombreProducto;

	@FieldWhere(columns = "c.cliente.nombre", likeMode = LikeMode.CONTAINS)
	private String nombreCliente;

	@FieldWhere(columns = "c.fecha")
	@BetweenDate(mode = ModeEnum.BEFORE_EQUALS)
	private Date fecha;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

}
