package com.capgemini.bankapp.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.repository.BankAaccountRepository;
import com.capgemini.bankapp.service.BankAccountService;
import com.capgemini.bankapp.util.DbUtil;
@Service
public class BankAccountServiceimpl implements BankAccountService {

	@Autowired
	 private BankAaccountRepository repository;
	 
	 
	@Autowired
	DbUtil dbutil;
	
//	public BankAccountServiceimpl(BankAaccountRepository repository) {
//		super();
//		this.repository = repository;
//	}

	@Override
	public double getBalance(long accountId) {
		return repository.getBalance(accountId);
	}

	
	
	
    @Autowired
	private void setRepository(BankAaccountRepository repository) {
		this.repository = repository;
	}

	@Override
	public double withdraw(long accountId, double amount) throws LowBalanceException {
		double newBalance=repository.getBalance(accountId)-amount;
        
		if(newBalance>=0)
		{
			 repository.updateBalance(accountId, newBalance);
		//newBalance=repository.getBalance(accountId)-amount;
		return newBalance;
		}
		else throw new LowBalanceException("You dont have money");
		
	}

	@Override
	public double deopsit(long accountId, double amount) {
      double balance=repository.getBalance(accountId);
      if(balance!=-1) {
     repository.updateBalance(accountId, amount+balance);
     return(amount+balance);
	
	}
      return -1;
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws LowBalanceException {
		String query = "select accountId from customers where accountId =?";
		try (Connection connection = dbutil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, (int) toAcc);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				if (withdraw(fromAcc, amount) >= 0) {
					deopsit(toAcc, amount);
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
