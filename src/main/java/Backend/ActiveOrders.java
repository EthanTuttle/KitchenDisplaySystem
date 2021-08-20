package src.main.java.Backend;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * <b>ActiveOrders</b> class that represents the orders a restaurant sees 
 * that they have to make
 */
public class ActiveOrders {

    /**
     * Active orders for the restaurant to make
     */
    public static LinkedList<Customer> orders;


    /**
     * Instantiates active order object
     * @param aCustomer Customer to add to the queue
     */
    public ActiveOrders(Customer aCustomer) {

        orders = new LinkedList<Customer>();
        orders.add(aCustomer);

    }
    
    /**
     * Separate constructor for Active Orders when no initial customer is specified
     */
    public ActiveOrders() {
        orders = new LinkedList<Customer>();
    }

    /**
     * Adds a customer to the active orders list and sorts the order list
     * @param customer Customer to add to the active orders list
     */
    public void addCustomer(Customer customer){
        orders.add(customer);
        orders.sort(new OrderComparator());
    }

    /**
     * Removes a customer from the active orders list
     * @param customer Customer to remove from the active orders list
     */
    public void removeCustomer(Customer customer){
        orders.remove(customer);
        Iterator<Customer> it = orders.iterator();
        while (it.hasNext()){
            it.next().incrementTick();
        }
        orders.sort(new OrderComparator());
    }

    /**
     * Iterator to iterate through the active orders list
     * @return Customer iterator
     */
    public Iterator<Customer> getIterator() {
        return orders.iterator();
    }

    /**
     * Sorts the active orders list
     */
    public void sort() {
        orders.sort(new OrderComparator());
    }

}
