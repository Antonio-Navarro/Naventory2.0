package com.antoniojnavarro.naventory.services.testsuite;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.testconfig.NaventoryServicesTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NaventoryServicesTestConfig.class)
@Transactional //Necesario para que @Rollback funcione, y se considere el test como una única transacción. En otro caso, la transacción hará commit en el Servicio
@Rollback
@ActiveProfiles("inside")
public class TestServicioEjemplo {
	
	@Autowired
	private ServicioUsuario srvUusuario;

	@Test
	public void test() {
		Usuario user = srvUusuario.findById("anavarrodelamor@gmail.com");
		assertTrue("anavarrodelamor@gmail.com".equals(user.getEmail()));
	}

}
