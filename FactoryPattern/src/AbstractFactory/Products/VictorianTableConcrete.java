package AbstractFactory.Products;

public class VictorianTableConcrete implements Table {
    @Override
    public void use() {
        System.out.println("Using victorian table");
    }
}
