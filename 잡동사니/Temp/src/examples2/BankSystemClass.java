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
		System.out.println("������ : "+interestRate);;
		System.out.println("���¹�ȣ : "+account);;
		System.out.println("�̸� : "+name);;
		System.out.println("�ܾ� : "+balance);;
	}
}