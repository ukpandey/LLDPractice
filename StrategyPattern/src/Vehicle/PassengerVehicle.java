package Vehicle;

import Strategy.NormalDriveStrategyImpl;

public class PassengerVehicle extends Vehicle{
    public PassengerVehicle(){
        super(new NormalDriveStrategyImpl());
    }
}
