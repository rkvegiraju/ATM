package com.atm;

public class Withdrawal extends Transaction {
	private int amount;
	
	// initialize constructor
	public Withdrawal(int accountNum, Bank bankData, int amount) {
		super(accountNum, bankData);
		this.amount = amount;
	}
	
	// check if withdrawal possible
	public boolean isWithdrawalPossible() {
		return (getBankData().getAvailableBalance(getAccountNumber()) >= amount);
	}
	
	@Override
	public void execute() {
		if (isWithdrawalPossible()) {
			getBankData().debit(getAccountNumber(), amount);
		}
	}
	
}
