package com.capgemini.bankapp.config;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages= {"com.capgemini.bankapp"})//scans all classes for annotaions controller,service etc
public class AppConfig {

	@Bean
	public DataSource getDatasource() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/BANKACCOUNTS");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	
		
	@Bean
	public JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jdbcTemplate=new JdbcTemplate(getDatasource());
		return jdbcTemplate;
	}
	
	
}
