//package com.antoniojnavarro.naventory.model.entities;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//
//import com.antoniojnavarro.naventory.model.commons.GenericEntity;
//
//@Entity
//@Table(name = "authorities", uniqueConstraints = {@UniqueConstraint(columnNames= {"user_id","authority"})})
//public class Role implements GenericEntity {
//
//	private static final long serialVersionUID = 1L;
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	private String authority;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getAuthority() {
//		return authority;
//	}
//
//	public void setAuthority(String authority) {
//		this.authority = authority;
//	}
//
//}
