package src.main.java;

import java.util.LinkedList;

public class OrderItem
{
	private LinkedList<MenuItem> order;
	private int timeToMake;

	public OrderItem() {}
	public void createOrder(LinkedList<MenuItem> orderPlaced)
	{
		order = orderPlaced;
		timeToMake = 0;
		for (MenuItem item : orderPlaced)
		{
			timeToMake+= item.getTimeToMake();
		}
	}
	public void addItemToOrder(MenuItem itemOrdered)
	{
		order.add(itemOrdered);
	}
	public void removeItemFromOrder(int index)
	{
		order.remove(index);
	}
	public void placeOrder()
	{
		// Once the order is placed it gets set to a customer
		
	}
}
