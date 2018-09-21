package com.capgemini.bankapp.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bankapp.entities.BankAccount;
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
		 double balance = withdraw(fromAcc, amount);
			if (balance != -1) {
				if (deopsit(toAcc, amount) == -1) {
					deopsit(fromAcc, amount);
					return false;
				}
				return true;
			}
			return false;
	}




	@Override
	public boolean addBankAccount(BankAccount account) {
		return repository.addBankAccount(account);
	}




	@Override
	public BankAccount findBankAccountById(long accountId) {

		return repository.findBankAccountById(accountId);
	}




	@Override
	public List<BankAccount> findAllBankAccounts() {

	return repository.findAllBankAccounts();
	}




	@Override
	public BankAccount updateBankAccount(BankAccount account) {
		
		return repository.updateBankAccount(account);
	}




	@Override
	public boolean deleteABankAccount(long accountId) {
		return repository.deleteABankAccount(accountId);
	}

}
