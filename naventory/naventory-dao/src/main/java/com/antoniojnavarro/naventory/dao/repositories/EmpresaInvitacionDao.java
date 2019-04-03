package com.antoniojnavarro.naventory.dao.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.EmpresaInvitacion;
import com.antoniojnavarro.naventory.model.filters.EmpresaInvitacionSearchFilter;

public interface EmpresaInvitacionDao extends Dao<EmpresaInvitacion, EmpresaInvitacionSearchFilter, Integer> {

	@Query("select ei from EmpresaInvitacion ei where ei.cif = ?1 and ei.email= ?2 and ei.token=?3")
	EmpresaInvitacion findEmpresaInvitacionByCifAndEmailAndToken(String cif, String email, String token);

	@Modifying
	@Query("delete from EmpresaInvitacion ei where ei.cif = ?1 and ei.email= ?2")
	void deleteEmpresaInvitacionByCifAndEmail(String cif, String email);
}
