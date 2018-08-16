package com.antoniojnavarro.naventory.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.antoniojnavarro.naventory.model.commons.GenericEntity;

@Entity
@DynamicInsert
@Table(name = "proveedor")
public class Proveedor implements GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prov")
	private Integer idProv;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "email")
	private Usuario usuario;

	@Column(name = "nombre", length = 255, nullable = true)
	private String nombre;

	@Column(name = "nombre_com", length = 255, nullable = true)
	private String nombreCom;

	@Column(name = "direccion", length = 255, nullable = true)
	private String direccion;

	@Column(name = "ciudad", length = 255, nullable = true)
	private String ciudad;

	@Column(name = "provincia", length = 255, nullable = true)
	private String provincia;

	@Column(name = "pais", length = 255, nullable = true)
	private String pais;

	@Column(name = "cp", nullable = true)
	private Integer cp;
	
	@Column(name = "tel1", nullable = true)
	private Integer tel1;
	
	@Column(name = "tel2", nullable = true)
	private Integer tel2;
	
	@Column(name = "correo", length = 255, nullable = true)
	private String correo;

	public Integer getIdProv() {
		return idProv;
	}

	public void setIdProv(Integer idProv) {
		this.idProv = idProv;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result + ((cp == null) ? 0 : cp.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((idProv == null) ? 0 : idProv.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((nombreCom == null) ? 0 : nombreCom.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((tel1 == null) ? 0 : tel1.hashCode());
		result = prime * result + ((tel2 == null) ? 0 : tel2.hashCode());
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
		Proveedor other = (Proveedor) obj;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (cp == null) {
			if (other.cp != null)
				return false;
		} else if (!cp.equals(other.cp))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (idProv == null) {
			if (other.idProv != null)
				return false;
		} else if (!idProv.equals(other.idProv))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (nombreCom == null) {
			if (other.nombreCom != null)
				return false;
		} else if (!nombreCom.equals(other.nombreCom))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		if (tel1 == null) {
			if (other.tel1 != null)
				return false;
		} else if (!tel1.equals(other.tel1))
			return false;
		if (tel2 == null) {
			if (other.tel2 != null)
				return false;
		} else if (!tel2.equals(other.tel2))
			return false;
		return true;
	}


}
