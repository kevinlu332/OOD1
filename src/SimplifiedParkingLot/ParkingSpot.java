package SimplifiedParkingLot;

public class ParkingSpot {
    private Vehicle currV;
    private VehicleSize size;
    //Notice that compact cars can park in large spots
    // but large cars cannot park in compact spots.


    public ParkingSpot(VehicleSize size){
        this.size = size;
    }
    public boolean fit(Vehicle v){
        return currV==null && size.getSize() >= v.getSize().getSize();
    }

    public void park(Vehicle v) {
        currV = v;
    }

    public void leave() {
        currV = null;
    }

    public Vehicle getVehicle() {
        return currV;
    }

}
