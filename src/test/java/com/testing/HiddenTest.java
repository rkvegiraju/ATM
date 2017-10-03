package com.testing;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.atm.Account;
import com.atm.BalanceInquiry;
import com.atm.Bank;
import com.atm.Deposit;
import com.atm.Transaction;
import com.atm.Withdrawal;


public class HiddenTest 
    extends TestCase
{
	private  Account accounts[];
	Bank bank_data;
	Transaction inquiry;
	int amount;
	Transaction deposit;
	Transaction withdraw;
	
    public HiddenTest( String testName )
    {
        super( testName );

    }

    public static Test suite()
    {
        return new TestSuite( HiddenTest.class );
    }
    //Authenticating user
    public boolean authenticate(int accn_no,int pin){
    	return bank_data.authenticateUser(accn_no,pin);
    }
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    public void cleanUpStreams() {
        outContent.reset();
    }
    
    
    public void test1()
    {
        //Creating accounts
    	accounts = new Account[3];
        accounts[0]=new Account(101101234,1244,50000);
        accounts[1]=new Account(101101345,2325, 20000 );
        accounts[2]=new Account(10233243, 5618,350000);
        
        
        bank_data = new Bank(accounts);
        //check authentication
        assertFalse(authenticate(101101234, 1232));
        
        assertTrue(authenticate(101101234, 1244));     
        
		inquiry = new BalanceInquiry(accounts[0].getAccountNumber(), bank_data);
	    // verifying account number
		assertTrue(inquiry.getAccountNumber()==101101234);
		
		//verify current balance
		setUpStreams();
	    inquiry.execute();
	    assertEquals(outContent.toString(),"50000\n");
	    cleanUpStreams();  
		amount = inquiry.getBankData().getAvailableBalance(101101234);
		
		assertTrue(amount==50000);
		
		assertTrue(authenticate(10233243, 5618));
		inquiry = new BalanceInquiry(10233243,bank_data);		
		withdraw = new Withdrawal(10233243, bank_data,4000);
		withdraw.execute();
		amount = accounts[2].getCurrentBalance();
		
		//verify amount after withdrawal
		assertTrue(amount==346000);
		
        assertTrue(authenticate(101101345, 2325));
        inquiry = new BalanceInquiry(101101345,bank_data);        
        deposit = new Deposit(101101345, bank_data, 5600);
        deposit.execute();
        setUpStreams();
        //Verify amount after deposit
        inquiry.execute();
        assertEquals(outContent.toString(),"25600\n");
        cleanUpStreams();
        
        withdraw = new Withdrawal(101101345, bank_data, 1600);
        withdraw.execute();;
        amount = accounts[1].getCurrentBalance();
        
        //verify amount after Withdrawal
        assertTrue(amount == 24000);

		
		

    }
    public void test2(){
    	accounts = new Account[1];
    	accounts[0] = new Account(10233243,6514,350000);
    	
        bank_data = new Bank(accounts);
        
    	assertTrue(authenticate(10233243,6514));
        inquiry = new BalanceInquiry(10233243,bank_data);    
        withdraw = new Withdrawal(10233243, bank_data, 400000);
        withdraw.execute();;
        
        //verify if withdrawal fails
        setUpStreams();
        inquiry.execute();
        assertEquals(outContent.toString(),"350000\n");
        cleanUpStreams();  
        
        
        withdraw = new Withdrawal(10233243, bank_data, 300000);
        withdraw.execute();
        setUpStreams();        
        inquiry.execute();
        assertEquals(outContent.toString(),"50000\n");
        cleanUpStreams();  
    	
    	
    }
    
    public void test3(){
    	accounts = new Account[1];
    	accounts[0] = new Account(10233243,6514,350000);
        bank_data = new Bank(accounts);
        
    	 //verify credit function in Bank class
        bank_data.credit(accounts[0].getAccountNumber(), 500);
        int amount=bank_data.getAvailableBalance(accounts[0].getAccountNumber());
        assertTrue(amount == 350500);
        
   	 	//verify debit function in Bank class
        bank_data.debit(accounts[0].getAccountNumber(),100);
        amount=bank_data.getAvailableBalance(accounts[0].getAccountNumber());
        assertTrue(amount == 350400);      
        


    }
    
    public void test4(){
    	accounts = new Account[1];
    	accounts[0] = new Account(10133243,6514,350000);
        bank_data = new Bank(accounts);
        
        //verify credit function in Account class
        accounts[0].credit(100);
        int amount = accounts[0].getCurrentBalance();
        assertTrue(amount==350100);
        
        //verify debit function in Account class
        accounts[0].debit(350100);
        amount = accounts[0].getCurrentBalance();
        assertTrue(amount==0);
    }
    
}