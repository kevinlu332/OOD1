package SimplifiedParkingLot;

public class Truck  extends Vehicle{
    @Override
    public VehicleSize getSize(){
        return VehicleSize.Large;
    }
}
