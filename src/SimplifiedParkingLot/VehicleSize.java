package SimplifiedParkingLot;

public enum VehicleSize {
    Compact(1),Large(2);
    public int getSize(){
        return order;
    }
    private int order;
     VehicleSize(int order){
        this.order = order;
    }
}
