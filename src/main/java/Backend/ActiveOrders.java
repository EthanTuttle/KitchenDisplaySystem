package src.main.java.Backend;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * <b>ActiveOrders<b/> class that represents the orders a restaurant sees 
 * that they have to make
 */
public class ActiveOrders {

    /**
     * Active orders for the restaurant to make
     */
    private PriorityQueue<Customer> orders;

    /**
     * Instantiates active order object
     * @param aCustomer Customer to add to the queue
     */
    public ActiveOrders(Customer aCustomer) {

        orders = new PriorityQueue<Customer>();
        orders.add(aCustomer);

    }
    
    /**
     * Arranges queue
     */
    public void ArrangeQueue() {

    }

}
