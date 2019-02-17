package com.antoniojnavarro.naventory.model.filters;

import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.EntityFilter;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.LikeMode;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.LogicalOperatorBetweenNames;
import com.antoniojnavarro.naventory.model.commons.filters.annotations.FieldWhere.OperatorLikeNoneEnum;
import com.antoniojnavarro.naventory.model.entities.Proveedor;;

@EntityFilter(entity = Proveedor.class, abbr = "c")
public class ProveedorSearchFilter implements SearchFilter {

	private static final long serialVersionUID = 1L;

	@FieldWhere(columns = "c.idProv", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS)
	private Integer idProv;

	@FieldWhere(columns = "c.usuario.email", operatorIfLikeNone = OperatorLikeNoneEnum.EQUALS, logicalOperator = LogicalOperatorBetweenNames.AND)
	private String usuario;

	@FieldWhere(columns = "c.nombre", likeMode = LikeMode.CONTAINS)
	private String nombre;

	@FieldWhere(columns = "c.nombreCom", likeMode = LikeMode.CONTAINS)
	private String nombreCom;

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

	public Integer getIdProv() {
		return idProv;
	}

	public void setIdProv(Integer idProv) {
		this.idProv = idProv;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	@Override
	public boolean isEmpty() {
		return false;
	}
}
