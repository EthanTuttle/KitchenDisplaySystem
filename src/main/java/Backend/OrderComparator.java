package src.main.java.Backend;

import java.util.Comparator;

public class OrderComparator implements Comparator<OrderItem> {
    public int compare(OrderItem o1, OrderItem o2){
        if (o1.getTimeToMake()<o2.getTimeToMake()){
            return -1;
        }
        else if (o1.getTimeToMake()>o2.getTimeToMake()){
            return 1;
        }
        return 0;
    }
}
