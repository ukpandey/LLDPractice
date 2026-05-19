package AbstractFactory;

import AbstractFactory.Products.Chair;
import AbstractFactory.Products.Table;

public interface FurnitureFactory {
    Chair createChair();
    Table createTable();
}
