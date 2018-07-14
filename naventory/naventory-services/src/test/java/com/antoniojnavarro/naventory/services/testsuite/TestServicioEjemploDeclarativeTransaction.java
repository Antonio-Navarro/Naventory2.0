package com.antoniojnavarro.naventory.services.testsuite;
//package com.antoniojnavarro.naventory.services.testsuite;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.antoniojnavarro.naventory.services.api.ServicioEjemploAllMethodsWithoutTransaction;
//import com.antoniojnavarro.naventory.services.api.ServicioEjemploMethodWithoutTransaction;
//import com.antoniojnavarro.naventory.services.testconfig.NaventoryServicesTestConfig;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = NaventoryServicesTestConfig.class)
//@ActiveProfiles("inside")
//public class TestServicioEjemploDeclarativeTransaction {
//
//	@Autowired
//	private ServicioEjemploMethodWithoutTransaction srvEjemploMethodWithoutTransaction;
//
//	@Autowired
//	private ServicioEjemploAllMethodsWithoutTransaction srvEjemploAllMethodsWithoutTransaction;
//
//	@Test
//	public void test() {
//
//		this.srvEjemploMethodWithoutTransaction.withoutTransaction();
//
//		this.srvEjemploAllMethodsWithoutTransaction.oneMethodWithoutTransaction();
//
//		this.srvEjemploAllMethodsWithoutTransaction.twoMethodWithoutTransaction();
//	}
//
//}
