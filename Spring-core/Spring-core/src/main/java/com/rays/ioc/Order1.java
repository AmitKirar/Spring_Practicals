package com.rays.ioc;

public class Order1 {

	private Payment payment;
	private Inventory inventory;

	// Setter injection
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	// Setter injection
	public void setInventory(Inventory inventory) {
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
