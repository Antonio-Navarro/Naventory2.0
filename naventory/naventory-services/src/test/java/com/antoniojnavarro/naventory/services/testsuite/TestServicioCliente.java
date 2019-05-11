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

import com.antoniojnavarro.naventory.model.entities.Cliente;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.repository.repositories.ClienteRepository;
import com.antoniojnavarro.naventory.services.api.ServicioCliente;
import com.antoniojnavarro.naventory.services.impl.ServicioClienteImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestServicioCliente {

	@InjectMocks
	ServicioCliente srvCliente = new ServicioClienteImpl();

	@Mock
	ClienteRepository repo;

	@Test
	public void findAllTest() {

		List<Cliente> list = new ArrayList<Cliente>();
		Cliente c1 = new Cliente();
		c1.setCiudad("Murcia");
		c1.setCorreo("anavarrodelamor@gmail.com");
		c1.setCp(30420);
		c1.setDireccion("Liberdad");
		Empresa empresa = new Empresa("test111");
		empresa.setNombre("test1");
		empresa.setDomicilioSocial("direccion test");
		c1.setEmpresa(empresa);
		c1.setNombre("Antonio");
		c1.setNombreCom("Navarro");
		c1.setPais("España");
		c1.setProvincia("Murcia");
		c1.setTel1(123123123);

		Cliente c2 = new Cliente();
		c2.setCiudad("Murcia");
		c2.setCorreo("maria@gmail.com");
		c2.setCp(30420);
		c2.setDireccion("Liberdad");
		empresa.setNombre("test1");
		empresa.setDomicilioSocial("direccion test");
		c2.setEmpresa(empresa);
		c2.setNombre("Maria");
		c2.setNombreCom("Navarro");
		c2.setPais("España");
		c2.setProvincia("Murcia");
		c2.setTel1(123123123);

		Cliente c3 = new Cliente();
		c3.setCiudad("Murcia");
		c3.setCorreo("juan@gmail.com");
		c3.setCp(30420);
		c3.setDireccion("Liberdad");
		empresa.setNombre("test1");
		empresa.setDomicilioSocial("direccion test");
		c3.setEmpresa(empresa);
		c3.setNombre("Juan");
		c3.setNombreCom("Navarro");
		c3.setPais("España");
		c3.setProvincia("Murcia");
		c3.setTel1(123123123);

		list.add(c1);
		list.add(c2);
		list.add(c3);

		when(repo.findAll()).thenReturn(list);

		// test
		List<Cliente> clienteList = srvCliente.findAll();

		assertEquals(3, clienteList.size());
		verify(repo, times(1)).findAll();
	}

	@Test
	public void crearClienteTest() {
		Cliente c1 = new Cliente();
		c1.setCiudad("Murcia");
		c1.setCorreo("anavarrodelamor@gmail.com");
		c1.setCp(30420);
		c1.setDireccion("Liberdad");
		Empresa empresa = new Empresa("test111");
		empresa.setNombre("test1");
		empresa.setDomicilioSocial("direccion test");
		c1.setEmpresa(empresa);
		c1.setNombre("Antonio");
		c1.setNombreCom("Navarro");
		c1.setPais("España");
		c1.setProvincia("Murcia");
		c1.setTel1(123123123);
		srvCliente.saveOrUpdate(c1, false);
		verify(repo, times(1)).save(c1);
	}

}
