package com.antoniojnavarro.naventory.model.filters;

import java.util.Date;

import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.BetweenDate;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.BetweenDate.ModeEnum;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.EntityFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.LikeMode;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.OperatorLikeNoneEnum;
import com.antoniojnavarro.naventory.model.entities.Compra;;

@EntityFilter(entity = Compra.class, abbr = "c")
public class CompraSearchFilter implements SearchFilter {

	private static final long serialVersionUID = 1L;
	@FieldWhere(columns = "c.factura", likeMode = LikeMode.CONTAINS)
	private String factura;
	@FieldWhere(columns = "c.usuario.email", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private String usuario;

	@FieldWhere(columns = "c.idCompra", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Integer idCompra;

	@FieldWhere(columns = "c.proveedor.nombre", likeMode = LikeMode.CONTAINS)
	private String proveedor;

	@FieldWhere(columns = "c.producto.sku", likeMode = LikeMode.CONTAINS)
	private String producto;

	@FieldWhere(columns = "c.nombreProd", likeMode = LikeMode.CONTAINS)
	private String nombreProd;

	@FieldWhere(columns = "c.fecha")
	@BetweenDate(mode = ModeEnum.AFTER_EQUALS)
	private Date fechaIni;

	@FieldWhere(columns = "c.fecha")
	@BetweenDate(mode = ModeEnum.BEFORE_EQUALS)
	private Date fechaFin;

	@FieldWhere(columns = "c.descripcion", likeMode = LikeMode.CONTAINS)
	private String descripcion;

	@FieldWhere(columns = "c.cantidad", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Integer cantidad;

	@FieldWhere(columns = "c.descuento", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Float descuento;

	@FieldWhere(columns = "c.iva", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Float iva;

	@FieldWhere(columns = "c.unidad", likeMode = LikeMode.CONTAINS)
	private String unidad;

	@FieldWhere(columns = "c.precio", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Float precio;

	@FieldWhere(columns = "c.total", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Float total;

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

	public Integer getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getNombreProd() {
		return nombreProd;
	}

	public void setNombreProd(String nombreProd) {
		this.nombreProd = nombreProd;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
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
