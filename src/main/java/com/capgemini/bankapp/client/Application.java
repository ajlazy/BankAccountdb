package com.capgemini.bankapp.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.bankapp.config.AppConfig;
import com.capgemini.bankapp.controller.BankAccountController;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.service.BankAccountService;
import com.capgemini.bankapp.service.impl.BankAccountServiceimpl;
import com.capgemini.bankapp.util.DbUtil;
import com.google.protobuf.Service;

public class Application {

	public static void main(String[] args) throws LowBalanceException {
		//ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
		BankAccountController bankAccountController=context.getBean("bankAccountController",BankAccountController.class );
			
		DbUtil dbutil=context.getBean(DbUtil.class);
		dbutil.getConnection();
		System.out.println(bankAccountController.getBalance(12344));
	}

}
