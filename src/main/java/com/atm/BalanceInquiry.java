package com.atm;

public class BalanceInquiry extends Transaction {
	
	public BalanceInquiry(int accountNum, Bank bankData) {
		super(accountNum, bankData);
	}
	
	// Print current balance using System.out.println only
	@Override
	public void execute() {
		System.out.println(getBankData().getAvailableBalance(getAccountNumber()));
	}
	
}
