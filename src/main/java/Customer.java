package src.main.java;

import java.util.LinkedList;

public class Customer
{
	private LinkedList<OrderItem> allOrders;
	
	public Customer()
	{

	}
	public void placeOrder(OrderItem order)
	{
		allOrders.add(order);
	}
	public void cancelOrder(int index)
	{
		allOrders.remove(index);
	}
}
