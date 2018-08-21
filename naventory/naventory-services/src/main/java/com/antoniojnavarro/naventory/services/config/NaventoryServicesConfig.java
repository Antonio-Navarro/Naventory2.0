package com.antoniojnavarro.naventory.services.config;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antoniojnavarro.naventory.dao.commons.impl.JpaDao;

@Configuration
@ComponentScan(basePackages = { "com.antoniojnavarro.naventory.services.*", "com.antoniojnavarro.naventory.dao.*" })
@EnableTransactionManagement
/*
 * OJO: En los packageToScan del EntityManager ni en el basePackages del
 * EnableJpaRepositories se puede poner los paquetes con .*, no funciona y lo
 * sobreentiende ya.
 */
@EnableJpaRepositories(basePackages = "com.antoniojnavarro.naventory.dao.repositories", entityManagerFactoryRef = "emf", transactionManagerRef = "transactionManager", repositoryBaseClass = JpaDao.class)
public class NaventoryServicesConfig {

	/* Model Mapper que nos permitirá realizar un mapeo directo entidad-DTO */
	@Bean
	public ModelMapper modelMapper() {
		/*
		 * Si queremos personalizar los mapeos, podemos hacerlo en este punto, para los
		 * DTOs que consideremos http://modelmapper.org/user-manual/property-mapping/
		 */
		return new ModelMapper();
	}

	@Bean(name = "packageModelJPANaventory")
	public List<String> packageModelJPANaventory() {
		return Collections.singletonList("com.antoniojnavarro.naventory.model.entities");
	}
}
