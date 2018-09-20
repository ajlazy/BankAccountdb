package com.capgemini.bankapp.repository;

public interface BankAaccountRepository {
	public double getBalance(long accountId);
	public boolean updateBalance(long accountId, double newBalance);
}


