package src.main.java;

import java.util.LinkedList;

public class Customer
{
	private LinkedList<OrderItem> allOrders;
	OrderItem singletonOrder;

	public Customer()
	{
		singletonOrder = new OrderItem();
	}
	public void placeOrder()
	{
		allOrders.add(singletonOrder);
	}
	public void cancelOrder(int index)
	{
		allOrders.remove(index);
	}
	public void startNewOrder()
	{
		singletonOrder = new OrderItem();
	}
	public void addItemToOrder(MenuItem item)
	{
		singletonOrder.addItemToOrder(item);
	}
}
