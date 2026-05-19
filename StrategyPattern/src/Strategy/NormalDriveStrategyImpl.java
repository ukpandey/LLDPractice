package Strategy;

public class NormalDriveStrategyImpl implements DriveStrategy{
    @Override
    public void drive() {
        System.out.println("Normal Drive Strategy for normal cars");
    }
}
