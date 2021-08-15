package src.main.java.Backend;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * <b>ActiveOrders<b/> class that represents the orders a restaurant sees 
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

    public ActiveOrders() {
        orders = new LinkedList<Customer>();
    }
    
    /**
     * Arranges queue
     */
    public void ArrangeQueue() {

    }

    public void addCustomer(Customer customer){
        orders.add(customer);
        orders.sort(new OrderComparator());
    }

    public void removeCustomer(Customer customer){
        orders.remove(customer);
        Iterator<Customer> it = orders.iterator();
        while (it.hasNext()){
            it.next().incrementTick();
        }
        orders.sort(new OrderComparator());
    }

}
