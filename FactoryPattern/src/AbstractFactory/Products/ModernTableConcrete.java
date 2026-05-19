package AbstractFactory.Products;

public class ModernTableConcrete implements Table {
    @Override
    public void use() {
        System.out.println("Using modern table");
    }
}
