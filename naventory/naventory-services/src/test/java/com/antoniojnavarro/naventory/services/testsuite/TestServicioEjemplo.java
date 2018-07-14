package com.antoniojnavarro.naventory.services.testsuite;
//package com.antoniojnavarro.naventory.services.testsuite;
//
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.antoniojnavarro.naventory.model.filters.EjemploSearchFilter;
//import com.antoniojnavarro.naventory.services.api.ServicioEjemplo;
//import com.antoniojnavarro.naventory.services.testconfig.NaventoryServicesTestConfig;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = NaventoryServicesTestConfig.class)
//@Transactional //Necesario para que @Rollback funcione, y se considere el test como una única transacción. En otro caso, la transacción hará commit en el Servicio
//@Rollback
//@ActiveProfiles("inside")
//public class TestServicioEjemplo {
//	
//	@Autowired
//	private ServicioEjemplo srvEjemplo;
//
//	@Test
//	public void test() {
//		srvEjemplo.findBySearchFilter(new EjemploSearchFilter().nombre("prueba")).
//				stream().forEach(System.out::println);
//		assertTrue(true);
//	}
//
//}
