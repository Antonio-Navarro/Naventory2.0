package com.antoniojnavarro.naventory.services.testsuite;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.antoniojnavarro.naventory.dao.repositories.UsuarioDao;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.impl.ServicioUsuarioImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestServicioUsuario {

	@InjectMocks
	ServicioUsuario srvUsuario = new ServicioUsuarioImpl();

	@Mock
	UsuarioDao dao;

	@Test
	public void findAllTest() {

		List<Usuario> list = new ArrayList<Usuario>();

		Usuario u1 = new Usuario();
		u1.setEmail("anavarrodelamor@gmail.com");
		u1.setNombre("antonio");
		u1.setApellido("navarro");
		Empresa empresa = new Empresa("test111");
		empresa.setNombre("test1");
		empresa.setDomicilioSocial("direccion test");
		u1.setEmpresa(empresa);
		u1.setPassword("testing");
		
		Usuario u2 = new Usuario();
		u2.setEmail("juan@gmail.com");
		u2.setNombre("juan");
		u2.setApellido("sanchez");
		empresa.setNombre("test1");
		empresa.setDomicilioSocial("direccion test");
		u2.setEmpresa(empresa);
		u2.setPassword("testing");

		list.add(u1);
		list.add(u2);

		when(dao.findAll()).thenReturn(list);

		// test
		List<Usuario> usuarioList = srvUsuario.findAll();

		assertEquals(2, usuarioList.size());
		verify(dao, times(1)).findAll();
	}

	@Test
	public void crearUsuarioTest() {
		Usuario u1 = new Usuario();
		u1.setEmail("anavarrodelamor@gmail.com");
		u1.setNombre("antonio");
		u1.setApellido("navarro");
		Empresa empresa = new Empresa("test111");
		empresa.setNombre("test1");
		empresa.setDomicilioSocial("direccion test");
		u1.setEmpresa(empresa);
		u1.setPassword("testing");
		
		srvUsuario.saveOrUpdate(u1, false);
		verify(dao, times(1)).save(u1);
	}

}
