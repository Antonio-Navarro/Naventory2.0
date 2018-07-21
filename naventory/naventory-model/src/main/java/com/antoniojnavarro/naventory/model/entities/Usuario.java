package com.antoniojnavarro.naventory.model.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.List;

import com.antoniojnavarro.naventory.model.commons.GenericEntity;


@Entity
@Table(name = "usuario")
public class Usuario implements GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "email", length = 255, nullable = false)
	private String email;

	@Column(name = "password", length = 255, nullable = false)
	private String password;
	
	@Column(name = "nombre", length = 255)
	private String nombre;

	@Column(name = "apellido", length = 255)
	private String apellido;
	
	@Column(name = "empresa", length = 255)
	private String empresa;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_alta", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false)
	private Date fecha_alta;

	@Column(name = "foto_perf", length = 255)
	private String fotoPerf;
	
	@Column(name = "activo", length = 1)
	private Character activo;
	
	@Column(name = "admin", length = 1)
	private Character admin;
	
	@Column(name = "token", length = 255)
	private String token;
	
	@Column(name = "token_pass", length = 255)
	private String tokenPass;
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name="user_id")
//	private List<Role> roles;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Date getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public String getFotoPerf() {
		return fotoPerf;
	}

	public void setFotoPerf(String fotoPerf) {
		this.fotoPerf = fotoPerf;
	}

	public Character getActivo() {
		return activo;
	}

	public void setActivo(Character activo) {
		this.activo = activo;
	}

	public Character getAdmin() {
		return admin;
	}

	public void setAdmin(Character admin) {
		this.admin = admin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenPass() {
		return tokenPass;
	}

	public void setTokenPass(String tokenPass) {
		this.tokenPass = tokenPass;
	}

//	public List<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(List<Role> roles) {
//		this.roles = roles;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activo == null) ? 0 : activo.hashCode());
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((fecha_alta == null) ? 0 : fecha_alta.hashCode());
		result = prime * result + ((fotoPerf == null) ? 0 : fotoPerf.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((tokenPass == null) ? 0 : tokenPass.hashCode());
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
		Usuario other = (Usuario) obj;
		if (activo == null) {
			if (other.activo != null)
				return false;
		} else if (!activo.equals(other.activo))
			return false;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (fecha_alta == null) {
			if (other.fecha_alta != null)
				return false;
		} else if (!fecha_alta.equals(other.fecha_alta))
			return false;
		if (fotoPerf == null) {
			if (other.fotoPerf != null)
				return false;
		} else if (!fotoPerf.equals(other.fotoPerf))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (tokenPass == null) {
			if (other.tokenPass != null)
				return false;
		} else if (!tokenPass.equals(other.tokenPass))
			return false;
		return true;
	}
	
	
	
	
}
