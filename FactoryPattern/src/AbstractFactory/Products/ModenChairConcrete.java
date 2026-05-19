package AbstractFactory.Products;

public class ModenChairConcrete implements Chair {
    @Override
    public void sit() {
        System.out.println("Sitting on modern chair");
    }
}
