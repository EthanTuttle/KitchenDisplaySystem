package src.main.java.Backend;

import java.util.LinkedList;
import java.util.Date;

/**
 * <b>Customer<b/> class to that places <b>Orders<b/>
 */
public class Customer
{
	/**
	 * All orders that a customer orders
	 */
	private LinkedList<OrderItem> allOrders;

	/**
	 * One order that customer has made
	 */
	private OrderItem singletonOrder;

	/**
	 * Time to make the customer's order
	 */
	private int timeToMake;

	/**
	 * Name of customer
	 */
	private String name;
	/**
	 * Number of times a customer is in the queue when another order has finished
	 */
	private int ticksInQueue;
	/**
	 * See if an order has been placed
	 */
	private boolean orderPlaced;

	private Date timePlaced = null;

	/**
	 * Instantiate Customer object
	 */
	public Customer(String n)
	{
		allOrders = new LinkedList<OrderItem>();
		singletonOrder = new OrderItem();
		timeToMake = 0;
		name = n;
		ticksInQueue = 0;
		orderPlaced = false;
	}

	/**
	 * Customer places the order after adding their menu items
	 */
	public void placeOrder(Date time)
	{
		allOrders.add(singletonOrder);
		orderPlaced = true;
		// Create a new singletonOrder since customers may place more than one order
		// Ex Person A : Orders Combo Item A.
		// 		Person A : Also orders Combo Item B for their friend
		//		... and so on
		singletonOrder = new OrderItem();
		if (timePlaced == null || time.compareTo(timePlaced) < 0) {
			timePlaced = time;
		}
	}

	/**
	 * Cancel the order they have made
	 * @param index index of order to cancel
	 */
	public void cancelOrder(int index)
	{
		OrderItem orderRemoved = allOrders.remove(index);
		timeToMake-=orderRemoved.getTimeToMake();
	}

	/**
	 * Adds item to an order
	 * @param item item to add
	 */
	public void addItemToOrder(MenuItem item)
	{
		singletonOrder.addItemToOrder(item);
		timeToMake+= item.getTimeToMake();
		if (orderPlaced){
			ActiveOrders.orders.sort(new OrderComparator());
		}
	}

	/**
	 * Removes item from a specific index 
	 * @param index index of item to remove
	 */
	public void removeItemFromOrder(int index)
	{	
		timeToMake-=singletonOrder.getMenuItem(index).getTimeToMake();
		singletonOrder.removeItemFromOrder(index);
		if (orderPlaced){
			ActiveOrders.orders.sort(new OrderComparator());
		}
	}
	/**
	 * 
	 * @return Orders a customer has
	 */
	public LinkedList<OrderItem> getOrders()
	{
		return this.allOrders;
	}
	/**
	 * 
	 * @return Name of customer
	 */
	public String getName()
	{
		return this.name;
	}
	/**
	 * 
	 * @return Time to make order
	 */
	public int getTimeToMake()
	{
		return this.timeToMake;
	}

	public void incrementTick(){
		ticksInQueue++;
	}

	public int getTick(){
		return ticksInQueue;
	}


	public Date getTime() {
		return timePlaced;
	}
}
