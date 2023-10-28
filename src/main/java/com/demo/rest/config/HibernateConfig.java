package com.demo.rest.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.demo.rest")
public class HibernateConfig {
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("com.demo.rest");
		factoryBean.setHibernateProperties(hibernateProperties());
		return factoryBean;
	}
	
	@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource source = new DriverManagerDataSource();
		source.setDriverClassName("com.mysql.cj.jdbc.Driver");
		source.setUrl("jdbc:mysql://localhost:3306/rest_api");
		source.setUsername("root");
		source.setPassword("Vinod@6891");
		return source;
		
	}
	
	
	private Properties hibernateProperties() {
		
		Properties prop = new Properties();
		prop.setProperty(Environment.HBM2DDL_AUTO, "update");
		prop.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
		prop.setProperty(Environment.SHOW_SQL, "true");
		prop.setProperty(Environment.FORMAT_SQL, "true");
		
		return prop;
	}
	
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setGenerateDdl(true);
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setJpaVendorAdapter(adapter);
		factoryBean.setPackagesToScan("com.demo.rest");
		factoryBean.setDataSource(dataSource());
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}
	
	@Bean("transactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaManager = new JpaTransactionManager();
		jpaManager.setEntityManagerFactory(entityManagerFactory);
		return jpaManager;
	}
	

}
