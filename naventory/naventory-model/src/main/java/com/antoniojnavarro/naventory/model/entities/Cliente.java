package com.antoniojnavarro.naventory.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;

import com.antoniojnavarro.naventory.model.commons.GenericEntity;

@Entity
@DynamicInsert
@Table(name = "cliente")
public class Cliente implements GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Integer idCliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "email")
	private Usuario usuario;

	@Column(name = "nombre", length = 255, nullable = true)
	private String nombre;

	@Column(name = "nombre_com", length = 255, nullable = true)
	private String nombreCom;

	@Column(name = "nif", length = 255, nullable = true)
	private String nif;

	
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

	@Column(name = "descuento", nullable = true)
	private Float descuento;
	
	@Column(name = "iva", nullable = true)
	private Integer iva;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_alta", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",insertable=false)
	private Date fecha_alta;
	
	@Column(name = "observaciones", length = 255, nullable = true)
	private String observaciones;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result + ((cp == null) ? 0 : cp.hashCode());
		result = prime * result + ((descuento == null) ? 0 : descuento.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((fecha_alta == null) ? 0 : fecha_alta.hashCode());
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
		result = prime * result + ((iva == null) ? 0 : iva.hashCode());
		result = prime * result + ((nif == null) ? 0 : nif.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((nombreCom == null) ? 0 : nombreCom.hashCode());
		result = prime * result + ((observaciones == null) ? 0 : observaciones.hashCode());
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
		Cliente other = (Cliente) obj;
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
		if (descuento == null) {
			if (other.descuento != null)
				return false;
		} else if (!descuento.equals(other.descuento))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (fecha_alta == null) {
			if (other.fecha_alta != null)
				return false;
		} else if (!fecha_alta.equals(other.fecha_alta))
			return false;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		if (iva == null) {
			if (other.iva != null)
				return false;
		} else if (!iva.equals(other.iva))
			return false;
		if (nif == null) {
			if (other.nif != null)
				return false;
		} else if (!nif.equals(other.nif))
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
		if (observaciones == null) {
			if (other.observaciones != null)
				return false;
		} else if (!observaciones.equals(other.observaciones))
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
