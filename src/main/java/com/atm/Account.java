package com.atm;

public class Account {
	private int accountNum;
	private int pin;
	private int currentBalance;
	
	// initialize constructor
	public Account(int accountNum, int pin, int currentBalance) {
		this.accountNum = accountNum;
		this.pin = pin;
		this.currentBalance = currentBalance;
		
	}
	
	// Add your code here
	public boolean validatePin(int pin) {
		return (this.pin == pin);
	}
	
	public int getCurrentBalance() {
		return this.currentBalance;
	}
	
	public int getAccountNumber() {
		return accountNum;
	}
	
	public void credit(int amount) {
		currentBalance += amount;
	}
	
	public void debit(int amount) {
		currentBalance -= amount;
	}
	
}
