package src.main.java.Backend;

import java.util.PriorityQueue;

/**
 * <b>ActiveOrders<b/> class that represents the orders a restaurant sees 
 * that they have to make
 */
public class ActiveOrders {

    /**
     * Active orders for the restaurant to make
     */
    public static PriorityQueue<Customer> orders;

    /**
     * Instantiates active order object
     * @param aCustomer Customer to add to the queue
     */
    public ActiveOrders(Customer aCustomer) {

        orders = new PriorityQueue<Customer>();
        orders.add(aCustomer);

    }

    public ActiveOrders() {
        orders = new PriorityQueue<Customer>();
    }
    
    /**
     * Arranges queue
     */
    public void ArrangeQueue() {

    }

    public void addCustomer(Customer customer){
        orders.add(customer);
    }

    public void removeCustomer(Customer customer){
        orders.remove(customer);
    }

}
