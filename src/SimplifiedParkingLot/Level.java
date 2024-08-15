package SimplifiedParkingLot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//One level of a parking lot will have multiple spots.
// Let's assume half of them are compact and
// the others being large ones.
// If there are odd number of spots, make the large spot more.
public class Level {
    private final List<ParkingSpot> spots;
    public Level(int numOfSpots) {
        List<ParkingSpot> list = new ArrayList<>();
        for(int i =0; i < numOfSpots/2; i++){
            list.add(new ParkingSpot(VehicleSize.Compact));
        }
        for(int i =0; i < numOfSpots - numOfSpots/2; i++){
            list.add(new ParkingSpot(VehicleSize.Large));
        }
        spots = Collections.unmodifiableList(list);
    }

    public boolean hasSpot(Vehicle v) {
        for(ParkingSpot p: spots){
            if(p.fit(v)) return true;
        }
        return false;
    }

    public boolean park(Vehicle v) {
        for(ParkingSpot p: spots){
            if(p.fit(v)) {
                p.park(v);
                return true;
            }
        }
        return false;
    }

    public boolean leave(Vehicle v) {
        for(ParkingSpot p: spots){
            if(p.getVehicle()==v){
                p.leave();
                return true;
            }
        }
        return false;
    }
}
