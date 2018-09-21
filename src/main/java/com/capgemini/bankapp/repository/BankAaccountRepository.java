package com.capgemini.bankapp.repository;



import java.util.List;

import com.capgemini.bankapp.entities.BankAccount;

public interface BankAaccountRepository {
	public double getBalance(long accountId);
	public boolean updateBalance(long accountId, double newBalance);
	public boolean addBankAccount(BankAccount account);
	public BankAccount findBankAccountById(long accountId);
	public List<BankAccount> findAllBankAccounts();
	public BankAccount updateBankAccount(BankAccount account);
	public boolean deleteABankAccount(long accountId);
}


