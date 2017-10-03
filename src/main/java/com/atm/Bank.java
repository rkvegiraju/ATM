package com.atm;

public class Bank {
	private Account[] accounts;
	
	// initialize constructor
	public Bank(Account accounts[]) {
		this.accounts = accounts;
	}
	
	// return the account for the corresponding account number
	private Account getAccount(int accountNum) {
		if (accounts != null && accounts.length > 0) {
			for (Account account : accounts) {
				if (account.getAccountNumber() == accountNum) {
					return account;
				}
			}
		}
		
		return null;
	}
	
	// Add your code here
	public boolean authenticateUser(int accountNum, int pin) {
		Account account = getAccount(accountNum);
		if (account != null && account.getAccountNumber() == accountNum && account.validatePin(pin)) {
			return true;
		}
		return false;
	}
	
	public int getAvailableBalance(int accountNum) {
		int balance = 0;
		Account account = getAccount(accountNum);
		if (account != null) {
			balance = account.getCurrentBalance();
		}
		
		// if (accounts != null && accounts.length > 0) {
		// for (Account account : accounts) {
		// balance += account.getCurrentBalance();
		// }
		// }
		
		return balance;
	}
	
	public void credit(int accountNum, int amount) {
		Account account = getAccount(accountNum);
		if (account == null) {
			// Throw Exception.
			return;
		}
		
		account.credit(amount);
	}
	
	public void debit(int accountNum, int amount) {
		Account account = getAccount(accountNum);
		if (account == null) {
			// Throw Exception.
			return;
		}
		
		account.debit(amount);
	}
	
}
