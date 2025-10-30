package com.rays.ioc;

public class Order2 {

	private Payment payment;
	private Inventory inventory;

	
	// Constructor injection
	public Order2(Payment payment, Inventory inventory) {
		this.payment = payment;
		this.inventory = inventory;

	}
	
	
	public void bookATicket(int items) {

		int price = 10;

		double totalAmount = items * price;

		double updatedBalance = payment.makePayment(totalAmount);

		int updatedStock = inventory.sold(items);

		System.out.println("tickets are booked");
		System.out.println("total amount paid :" + totalAmount);
		System.out.println("remaining balance :" + updatedBalance);
		System.out.println("total booked tickets :" + items);
		System.out.println("updated stocks :" + updatedStock);

	}

}
