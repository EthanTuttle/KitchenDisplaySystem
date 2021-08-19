package src.main.java.Backend;

import java.util.Comparator;
/**
 * <b>Order Comparator<b/> class used to order the Active Orders List
 */
public class OrderComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer c1, Customer c2){
        if ((c1.getTick()>=5 && c2.getTick()>=5) || (c1.getTick()<5 && c2.getTick()<5)){
            if (c1.getTimeToMake()<c2.getTimeToMake()){
                return -1;
            }
            else if (c1.getTimeToMake()>c2.getTimeToMake()){
                return 1;
            }
            return 0;
        }
        else if (c1.getTick()>=5 && c2.getTick()<5){
            return -1;
        }
        else if (c1.getTick()<5 && c2.getTick()>=5){
            return 1;
        }

        return 0;
    }

    
}
