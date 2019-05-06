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

import com.antoniojnavarro.naventory.dao.repositories.ProductoDao;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.services.api.ServicioProducto;
import com.antoniojnavarro.naventory.services.impl.ServicioProductoImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestServicioProducto {

	@InjectMocks
	ServicioProducto srvProducto = new ServicioProductoImpl();

	@Mock
	ProductoDao dao;

	@Test
	public void findAllTest() {

		List<Producto> list = new ArrayList<Producto>();

		Producto p = new Producto();
		Empresa empresa = new Empresa("test111");
		empresa.setNombre("test1");
		empresa.setDomicilioSocial("direccion test");
		p.setSku("test1");
		p.setNombre("categoria test");
		p.setEmpresa(empresa);

		Producto p1 = new Producto();
		empresa.setNombre("test1");
		empresa.setDomicilioSocial("direccion test");
		p1.setSku("test1");
		p1.setNombre("categoria test");
		p1.setEmpresa(empresa);


		list.add(p);
		list.add(p1);

		when(dao.findAll()).thenReturn(list);

		// test
		List<Producto> productoList = srvProducto.findAll();

		assertEquals(2, productoList.size());
		verify(dao, times(1)).findAll();
	}

	@Test
	public void crearProductoTest() {
		Producto p = new Producto();
		Empresa empresa = new Empresa("test111");
		empresa.setNombre("test1");
		empresa.setDomicilioSocial("direccion test");
		p.setSku("test1");
		p.setNombre("categoria test");
		p.setEmpresa(empresa);
		srvProducto.saveOrUpdate(p, false);
		verify(dao, times(1)).save(p);
	}

}
