package com.capgemini.bankapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Component
@PropertySource("classpath:config.properties")
public class DbUtil {
	
//	
//	  @Value("${forname}") 
//	  private String  propertyField;
	  @Value("${database}") 
	  private String dburl;
	  @Value("${dbuser}")
	  private String username;
	  @Value("${dbpassword}")
	  private String password;
	

	
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(dburl, username, password);
			System.out.println(dburl+"     "+password);
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}