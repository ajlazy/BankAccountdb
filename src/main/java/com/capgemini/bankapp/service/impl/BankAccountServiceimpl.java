package com.capgemini.bankapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.repository.BankAaccountRepository;
import com.capgemini.bankapp.service.BankAccountService;
@Service
public class BankAccountServiceimpl implements BankAccountService {

	 private BankAaccountRepository repository;
	 
	 
	 
	
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
	public boolean fundTransfer(long fromAccount, long toAccount, double amount) throws LowBalanceException {
		double balance=withdraw(fromAccount, amount);
		if(balance!=-1)
		{
			if(deopsit(toAccount,amount)!=-1)
			{
				deopsit(fromAccount, amount);
				return false;
			}
			return true;
		}
		return false;
	}

}
