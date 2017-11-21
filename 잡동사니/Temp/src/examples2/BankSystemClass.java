package examples2;

import java.util.Scanner;

public class BankSystemClass{
	private double interestRate;
	private String account;
	private String name;
	private int balance;
	
	public BankSystemClass(double interestRate, String account, String name, int balance) {
		this.interestRate=interestRate;
		this.account=account;
		this.name=name;
		this.balance=balance;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	void deposit(int money) {
		this.balance+=money;
	}
	void withdraw(int money) {
		this.balance-=money;
	}
	void calBalance(int date) {
		this.balance+=this.balance*this.interestRate*date;
	}
	void printAccount() {
		System.out.println("이자율 : "+interestRate);;
		System.out.println("계좌번호 : "+account);;
		System.out.println("이름 : "+name);;
		System.out.println("잔액 : "+balance);;
	}
}