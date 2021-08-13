package src.main.java;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ActiveOrders {
    private PriorityQueue<Customer> orders;

    public ActiveOrders(Customer aCustomer) {
        
        orders = new PriorityQueue<Customer>();

        orders.add(aCustomer);

    }

    public void ArrangeQueue() {

    }

}
