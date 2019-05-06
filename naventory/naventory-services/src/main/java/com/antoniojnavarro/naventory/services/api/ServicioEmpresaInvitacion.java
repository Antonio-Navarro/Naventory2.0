package com.antoniojnavarro.naventory.services.api;

import com.antoniojnavarro.naventory.model.entities.EmpresaInvitacion;
import com.antoniojnavarro.naventory.model.filters.EmpresaInvitacionSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;

public interface ServicioEmpresaInvitacion
		extends ServicioCrud<EmpresaInvitacion, EmpresaInvitacionSearchFilter, Integer> {
	EmpresaInvitacion findEmpresaInvitacionByCifAndEmailAndToken(String cif, String email, String token);

	void deleteEmpresaInvitacionByCifAndEmail(String cif, String email);
}
