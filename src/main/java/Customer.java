package src.main.java;

import java.util.LinkedList;

public class Customer
{
	private LinkedList<OrderItem> allOrders;
	private OrderItem singletonOrder;
	private String name;
	private int timeToMake;

	public Customer()
	{
		allOrders = new LinkedList<OrderItem>();
		singletonOrder = new OrderItem();
		timeToMake = 0;
	}
	public void placeOrder()
	{
		allOrders.add(singletonOrder);
		timeToMake+=singletonOrder.getTimeToMake();
		// Create a new singletonOrder since customers may place more than one order
		// Ex Person A : Orders Combo Item A.
		// 		Person A : Also orders Combo Item B for their friend
		//		... and so on
		singletonOrder = new OrderItem();
	}
	public void cancelOrder(int index)
	{
		OrderItem orderRemoved = allOrders.remove(index);
		timeToMake-=orderRemoved.getTimeToMake();
	}
	public void addItemToOrder(MenuItem item)
	{
		singletonOrder.addItemToOrder(item);
		// Don't change the time because the time is only added once the order has been placed
	}
	public void removeItemFromOrder(int index)
	{
		singletonOrder.removeItemFromOrder(index);
		// Don't change the time because the time is only added once the order has been placed
	}
	public LinkedList<OrderItem> getOrders()
	{
		return this.allOrders;
	}
	public String getName()
	{
		return this.name;
	}
	public int getTimeToMake()
	{
		return this.timeToMake;
	}
}
