package src.main.java.Backend;

import java.util.LinkedList;

/**
 * <b>Order Item<b/> class that contains <b>menu items<b/> that a <b>Customer<b/> adds to the order
 * and the time to make that entire <b>Order item<b/>
 */
public class OrderItem
{
	/**
	 * List of menu items in order
	 */
	private LinkedList<MenuItem> order;
	/**
	 * Time to make the whole order
	 */
	private int timeToMake;

	/**
	 * Instantiates order item object
	 */
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

	/**
	 * Adds menu item to a order
	 * @param itemOrdered Item to add to order
	 */
	public void addItemToOrder(MenuItem itemOrdered)
	{
		order.add(itemOrdered);
		timeToMake+=itemOrdered.getTimeToMake();
	}

	/**
	 * Removes menu item from a order
	 * @param index Index of menu item to remove
	 */
	public void removeItemFromOrder(int index)
	{
		MenuItem itemRemoved = order.remove(index);
		timeToMake-=itemRemoved.getTimeToMake();
	}

	/**
	 * 
	 * @return Order of a customer
	 */
	public LinkedList<MenuItem> getOrder()
	{
		return this.order;	
	}

	/**
	 * 
	 * @return Time to Make in an order
	 */
	public int getTimeToMake()
	{
		return timeToMake;
	}
}