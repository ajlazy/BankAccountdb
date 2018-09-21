package com.capgemini.bankapp.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.bankapp.config.AppConfig;
import com.capgemini.bankapp.controller.BankAccountController;
import com.capgemini.bankapp.entities.BankAccount;
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
		System.out.println("hiiiiiiiiiiiii");
		System.out.println(bankAccountController.getBalance(12345));
		System.out.println(bankAccountController.findAllBankAccounts());
		System.out.println(bankAccountController.deposit(12345, 5000));
		//bankAccountController.deleteABankAccount(12344);
		BankAccount account=new BankAccount(12345, "AKSHIT JAIN", "SAVINGS", 100);
		bankAccountController.updateBankAccount(account);
		bankAccountController.fundTransfer(12343,12345 , 20000);
	}

}
