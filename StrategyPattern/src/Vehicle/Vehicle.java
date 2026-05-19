package Vehicle;

import Strategy.DriveStrategy;

public class Vehicle {
    DriveStrategy strategy;
    public Vehicle(DriveStrategy strategy){
        this.strategy = strategy;
    }
    public void drive(){
        strategy.drive();
    }
}
