package com.antoniojnavarro.naventory.services.api;

import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.UsuarioSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioUsuario extends ServicioCrud<Usuario, UsuarioSearchFilter, String> {

	Usuario findUsuarioByEmail(String email) throws ServicioException;

	boolean existsUsuarioByEmail(String email) throws ServicioException;

	Usuario findUsuarioByEmailAndPassword(String email, String pass) throws ServicioException;

	Object[] findUsersGrafica();

	void validateEmail(String email) throws ServicioException;

	void validateAndEmailOpcional(Usuario entity, boolean validarEmail) throws ServicioException;

	Usuario findUsuarioByEmailAndActivo(String email, String activo) throws ServicioException;
}
