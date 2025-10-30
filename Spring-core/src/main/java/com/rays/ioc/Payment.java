package com.rays.ioc;

import org.springframework.beans.factory.annotation.Value;

public class Payment {
   
	double balance = 0.0;

	public double getBalance() {
		return balance;
	}

	 @Value(value="100")// <!-- <property name="balance" value="100"></property> -->
	public void setBalance(double balance) {
		this.balance = balance;

	}

	public double makePayment(double amt) {
		balance = balance - amt;
		return balance;
	}

	public double deposit(double amt) {
		balance = balance + amt;
		return balance;

	}
}
