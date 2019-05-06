package com.antoniojnavarro.naventory.model.filters;

import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.EntityFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.LikeMode;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.LogicalOperatorBetweenNames;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.OperatorLikeNoneEnum;
import com.antoniojnavarro.naventory.model.entities.Producto;;

@EntityFilter(entity = Producto.class, abbr = "c")
public class ProductoSearchFilter implements SearchFilter {

	private static final long serialVersionUID = 1L;

	@FieldWhere(columns = "c.sku", likeMode = LikeMode.CONTAINS)
	private String sku;

	@FieldWhere(columns = "c.empresa.cif", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS, logicalOperator = LogicalOperatorBetweenNames.AND)
	private String empresa;

	@FieldWhere(columns = "c.categoria.idCat", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Integer categoria;

	@FieldWhere(columns = "c.proveedor.idProv", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Integer proveedor;

	@FieldWhere(columns = "c.nombre", likeMode = LikeMode.CONTAINS)
	private String nombre;

	@FieldWhere(columns = "c.descripcion", likeMode = LikeMode.CONTAINS)
	private String descripcion;

	@FieldWhere(columns = "c.unidad", likeMode = LikeMode.CONTAINS)
	private String unidad;

	@FieldWhere(columns = "c.coste", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Float coste;

	@FieldWhere(columns = "c.precio", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Float precio;

	@FieldWhere(columns = "c.stock", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Integer stock;

	@FieldWhere(columns = "c.stockMin", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Integer stockMin;

	@FieldWhere(columns = "c.observaciones", likeMode = LikeMode.CONTAINS)
	private String observaciones;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public Integer getProveedor() {
		return proveedor;
	}

	public void setProveedor(Integer proveedor) {
		this.proveedor = proveedor;
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

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public Float getCoste() {
		return coste;
	}

	public void setCoste(Float coste) {
		this.coste = coste;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getStockMin() {
		return stockMin;
	}

	public void setStockMin(Integer stockMin) {
		this.stockMin = stockMin;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
