package com.antoniojnavarro.naventory.model.filters;

import java.util.Date;

import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.EntityFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.LikeMode;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.LogicalOperatorBetweenNames;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.OperatorLikeNoneEnum;
import com.antoniojnavarro.naventory.model.entities.Cliente;;

@EntityFilter(entity = Cliente.class, abbr = "c")
public class ClienteSearchFilter implements SearchFilter {

	private static final long serialVersionUID = 1L;

	@FieldWhere(columns = "c.idCliente", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Integer idCliente;

	@FieldWhere(columns = "c.empresa.cif", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS, logicalOperator = LogicalOperatorBetweenNames.AND)
	private String empresa;

	@FieldWhere(columns = "c.nombre", likeMode = LikeMode.CONTAINS)
	private String nombre;

	@FieldWhere(columns = "c.nombreCom", likeMode = LikeMode.CONTAINS)
	private String nombreCom;

	@FieldWhere(columns = "c.nif", likeMode = LikeMode.CONTAINS)
	private String nif;

	@FieldWhere(columns = "c.direccion", likeMode = LikeMode.CONTAINS)
	private String direccion;

	@FieldWhere(columns = "c.ciudad", likeMode = LikeMode.CONTAINS)
	private String ciudad;

	@FieldWhere(columns = "c.provincia", likeMode = LikeMode.CONTAINS)
	private String provincia;

	@FieldWhere(columns = "c.pais", likeMode = LikeMode.CONTAINS)
	private String pais;

	@FieldWhere(columns = "c.cp", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Integer cp;

	@FieldWhere(columns = "c.tel1", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Integer tel1;

	@FieldWhere(columns = "c.tel2", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Integer tel2;

	@FieldWhere(columns = "c.correo", likeMode = LikeMode.CONTAINS)
	private String correo;

	@FieldWhere(columns = "c.descuento", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Float descuento;

	@FieldWhere(columns = "c.iva", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Integer iva;

	@FieldWhere(columns = "c.fecha_alta", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Date fecha_alta;

	@FieldWhere(columns = "c.observaciones", likeMode = LikeMode.CONTAINS)
	private String observaciones;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreCom() {
		return nombreCom;
	}

	public void setNombreCom(String nombreCom) {
		this.nombreCom = nombreCom;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Integer getCp() {
		return cp;
	}

	public void setCp(Integer cp) {
		this.cp = cp;
	}

	public Integer getTel1() {
		return tel1;
	}

	public void setTel1(Integer tel1) {
		this.tel1 = tel1;
	}

	public Integer getTel2() {
		return tel2;
	}

	public void setTel2(Integer tel2) {
		this.tel2 = tel2;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Float getDescuento() {
		return descuento;
	}

	public void setDescuento(Float descuento) {
		this.descuento = descuento;
	}

	public Integer getIva() {
		return iva;
	}

	public void setIva(Integer iva) {
		this.iva = iva;
	}

	public Date getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
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
