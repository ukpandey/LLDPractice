package AbstractFactory;

import AbstractFactory.Products.Chair;
import AbstractFactory.Products.Table;
import AbstractFactory.Products.VictorianChairConcrete;
import AbstractFactory.Products.VictorianTableConcrete;

public class VictorianFurnitureFactoryImpl implements FurnitureFactory{
    @Override
    public Table createTable() {
        return new VictorianTableConcrete();
    }

    @Override
    public Chair createChair() {
        return new VictorianChairConcrete();
    }
}
