package src.main.java;

import java.util.LinkedList;

public class OrderItem
{
	private LinkedList<MenuItem> order;
	private int timeToMake;

	public OrderItem()
	{
		order = new LinkedList<MenuItem>();
		timeToMake = 0;
	}
	/**
	 * Create a new order with a prebuilt list of items.
	 * @param orderPlaced A prebuild order
	 */
	public void createOrder(LinkedList<MenuItem> orderPlaced)
	{
		order = orderPlaced;
		for (MenuItem item : orderPlaced)
		{
			timeToMake+= item.getTimeToMake();
		}
	}
	public void addItemToOrder(MenuItem itemOrdered)
	{
		order.add(itemOrdered);
		timeToMake+=itemOrdered.getTimeToMake();
	}
	public void removeItemFromOrder(int index)
	{
		MenuItem itemRemoved = order.remove(index);
		timeToMake-=itemRemoved.getTimeToMake();
	}
	public LinkedList<MenuItem> getOrder()
	{
		return this.order;	
	}
	public int getTimeToMake()
	{
		return timeToMake;
	}
}