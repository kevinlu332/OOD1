package SimplifiedParkingLot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestPLSolution {
    public void testParkingLot() throws Exception {
        ParkingLot lot = new ParkingLot(4, 10);
        List< Vehicle > list = new ArrayList< >();
        LinkedList cars = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            final Vehicle v = i < 15 ? new Truck() : new Car();
            boolean hasSpot = lot.hasSpot(v);
            if (i >= 40) {
                check(hasSpot == false);
            } else {
                check(hasSpot == true);
                check(lot.park(v) == true);
                cars.add(i);
            }
            list.add(v);
        }
        int i = 0;
        for (Vehicle v : list) {
            if (i >= 40) {
                check(lot.leave(v) == false);
            } else {
                check(lot.leave(v) == true);
                cars.removeFirst();
                check(lot.leave(v) == false);
            }
            i++;
        }
    }
    public void check(boolean pass) {
        if (!pass) throw new RuntimeException("Test failed");
    }



        public static void main(String[] args) throws Exception {
            TestPLSolution ts = new TestPLSolution();
        ts.testParkingLot();
    }
}
