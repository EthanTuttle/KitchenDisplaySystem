package src.main.java.Backend;

import java.util.Comparator;

public class OrderComparator implements Comparator<Customer> {
    public int compare(Customer c1, Customer c2){
        if (c1.getTimeToMake()<c2.getTimeToMake()){
            return -1;
        }
        else if (c1.getTimeToMake()>c2.getTimeToMake()){
            return 1;
        }
        return 0;
    }
}
