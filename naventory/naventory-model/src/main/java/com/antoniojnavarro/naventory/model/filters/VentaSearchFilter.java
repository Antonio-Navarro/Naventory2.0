package com.antoniojnavarro.naventory.model.filters;

import java.util.Date;

import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.BetweenDate;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.EntityFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.LikeMode;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.LogicalOperatorBetweenNames;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.OperatorLikeNoneEnum;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.OrderByColumn;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.OrderByMultipleColumns;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.BetweenDate.ModeEnum;
import com.antoniojnavarro.naventory.model.entities.Venta;;

@EntityFilter(entity = Venta.class, abbr = "c")
@OrderByMultipleColumns({ @OrderByColumn(column = "c.fecha"), @OrderByColumn(column = "c.cantidad") })
public class VentaSearchFilter implements SearchFilter {

	private static final long serialVersionUID = 1L;

	@FieldWhere(columns = "c.cantidad", likeMode = LikeMode.NONE)
	private Integer cantidad;
	
	@FieldWhere(columns = "c.empresa.cif", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS, logicalOperator = LogicalOperatorBetweenNames.AND)
	private String empresa;

	@FieldWhere(columns = "c.producto.nombre", likeMode = LikeMode.CONTAINS)
	private String nombreProducto;

	@FieldWhere(columns = "c.cliente.nombre", likeMode = LikeMode.CONTAINS)
	private String nombreCliente;

	@FieldWhere(columns = "c.fecha")
	@BetweenDate(mode = ModeEnum.AFTER_EQUALS)
	private Date fechaIni;

	@FieldWhere(columns = "c.fecha")
	@BetweenDate(mode = ModeEnum.BEFORE_EQUALS)
	private Date fechaFin;

	@FieldWhere(columns = "c.formaPago.idPago", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Integer formaPago;

	@FieldWhere(columns = "c.descuento", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Float descuento;

	@FieldWhere(columns = "c.iva", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Float iva;

	@FieldWhere(columns = "c.precio", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Float precio;

	@FieldWhere(columns = "c.total", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Float total;

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public Integer getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(Integer formaPago) {
		this.formaPago = formaPago;
	}

	public Float getDescuento() {
		return descuento;
	}

	public void setDescuento(Float descuento) {
		this.descuento = descuento;
	}

	public Float getIva() {
		return iva;
	}

	public void setIva(Float iva) {
		this.iva = iva;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
	@Override
	public boolean isEmpty() {
		return false;
	}
}
