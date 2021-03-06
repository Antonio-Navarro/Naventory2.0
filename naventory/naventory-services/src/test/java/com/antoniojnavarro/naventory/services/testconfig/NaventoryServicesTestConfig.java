package com.antoniojnavarro.naventory.services.testconfig;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antoniojnavarro.naventory.repository.commons.impl.JpaBaseRepository;

@Configuration
@ComponentScan(basePackages = { "com.antoniojnavarro.naventory.services.*", "com.antoniojnavarro.naventory.repository.*" })
@PropertySources({ @PropertySource(value = "classpath:naventory-app.properties", ignoreResourceNotFound = false) })
@EnableAspectJAutoProxy
@EnableTransactionManagement
/*
 * OJO: En los packageToScan del EntityManager ni en el basePackages del
 * EnableJpaRepositories se puede poner los paquetes con .*, no funciona y lo
 * sobreentiende ya.
 */
@EnableJpaRepositories(basePackages = "com.antoniojnavarro.naventory.repository.repositories", entityManagerFactoryRef = "emf", transactionManagerRef = "transactionManager", repositoryBaseClass = JpaBaseRepository.class)
@Import({ DataSourceInsideConfig.class, DataSourceOutsideConfig.class })
@ImportResource(value = { "classpath:config/aop-config.xml" })
public class NaventoryServicesTestConfig {

	@Autowired
	private DataSource dataSource;

	@Bean(name = "emf")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPackagesToScan(packageEntitiesJPANaventory().stream().toArray(String[]::new));
		emf.setDataSource(this.dataSource);
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setJpaProperties(jpaProperties());
		return emf;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	/* Model Mapper que nos permitirá realizar un mapeo directo entidad-DTO */
	@Bean
	public ModelMapper modelMapper() {
		/*
		 * Si queremos personalizar los mapeos, podemos hacerlo en este punto, para los
		 * DTOs que consideremos
		 * http://modelmapper.org/user-manual/property-mapping/
		 */
		return new ModelMapper();
	}

	@Bean
	public List<String> packageEntitiesJPANaventory() {
		return Collections.singletonList("com.antoniojnavarro.naventory.model.entities");
	}

	/**
	 * Permite utilizar las anotaciones @Value con propiedades del sistema o
	 * propiedades que han sigo cargadas a partir de un fichero properties
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	Properties jpaProperties() {
		Properties jpaProps = new Properties();
		jpaProps.setProperty("hibernate.show_sql", "true");
		jpaProps.setProperty("hibernate.bytecode.use_reflection_optimizer", "true");
		jpaProps.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		jpaProps.setProperty("hibernate.hbm2ddl.auto", "validate");
		
		return jpaProps;
	}
}
