package com.antoniojnavarro.naventory.model.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.antoniojnavarro.naventory.model.commons.GenericEntity;
import com.antoniojnavarro.naventory.model.entities.enums.RolEnum;

@Entity
@Table(name = "Rol")
public class Role implements GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NOMBRE")
	@Enumerated(EnumType.STRING)
	private RolEnum rol;

	@Column(name = "DESCRIPCION", length = 255, nullable = true)
	private String descripcion;

	@ManyToMany(mappedBy = "roles")
	private List<Usuario> usuarios;

	public Role() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RolEnum getRole() {
		return rol;
	}

	public void setRole(RolEnum role) {
		this.rol = role;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
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
		Role other = (Role) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
}
