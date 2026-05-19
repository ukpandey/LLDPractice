package AbstractFactory.Products;

public class VictorianChairConcrete implements Chair {
    @Override
    public void sit() {
        System.out.println("Sitting on Victorian Chair");
    }
}
