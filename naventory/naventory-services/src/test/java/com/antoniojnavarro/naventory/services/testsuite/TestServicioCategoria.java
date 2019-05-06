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

import com.antoniojnavarro.naventory.dao.repositories.CategoriaDao;
import com.antoniojnavarro.naventory.model.entities.Categoria;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.services.api.ServicioCategoria;
import com.antoniojnavarro.naventory.services.impl.ServicioCategoriaImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestServicioCategoria {

	@InjectMocks
	ServicioCategoria srvCategoria = new ServicioCategoriaImpl();

	@Mock
	CategoriaDao dao;

	@Test
	public void findAllTest() {

		List<Categoria> list = new ArrayList<Categoria>();

		Categoria c = new Categoria();
		Empresa empresa = new Empresa("test111");
		empresa.setNombre("test1");
		empresa.setDomicilioSocial("direccion test");
		c.setEmpresa(empresa);
		c.setNomCat("Test");
		c.setIdCat(123);
		c.setDesc("test de categorias");

		Categoria c1 = new Categoria();
		c1.setEmpresa(empresa);
		c1.setNomCat("Test 2");
		c1.setIdCat(123);
		c1.setDesc("test de categorias");

		list.add(c1);
		list.add(c);

		when(dao.findAll()).thenReturn(list);

		// test
		List<Categoria> categoriaList = srvCategoria.findAll();

		assertEquals(2, categoriaList.size());
		verify(dao, times(1)).findAll();
	}

	@Test
	public void crearCategoriaTest() {
		Categoria c = new Categoria();
		Empresa empresa = new Empresa("test111");
		empresa.setNombre("test1");
		empresa.setDomicilioSocial("direccion test");
		c.setEmpresa(empresa);
		c.setNomCat("Test");
		c.setIdCat(123);
		c.setDesc("test de categorias");
		srvCategoria.saveOrUpdate(c, false);
		verify(dao, times(1)).save(c);
	}

}
