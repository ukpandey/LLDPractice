package AbstractFactory;

import AbstractFactory.Products.Chair;
import AbstractFactory.Products.ModenChairConcrete;
import AbstractFactory.Products.ModernTableConcrete;
import AbstractFactory.Products.Table;

public class ModernFurnitureFactoryImpl implements FurnitureFactory{
    @Override
    public Chair createChair() {
        return new ModenChairConcrete();
    }

    @Override
    public Table createTable() {
        return new ModernTableConcrete();
    }
}
