package com.atm;

public class Deposit extends Transaction {
	private int amount;
	
	public Deposit(int accountNum, Bank bankData, int amount) {
		super(accountNum, bankData);
		this.amount = amount;
	}
	
	@Override
	public void execute() {
		// Add your code here
		getBankData().credit(getAccountNumber(), amount);
	}
	
}
