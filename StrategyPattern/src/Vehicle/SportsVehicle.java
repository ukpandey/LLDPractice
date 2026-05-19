package Vehicle;

import Strategy.SportsDriveStrategyImpl;

public class SportsVehicle extends Vehicle{
    public SportsVehicle(){
        super(new SportsDriveStrategyImpl());
    }
}
