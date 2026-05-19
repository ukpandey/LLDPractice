import AbstractFactory.FurnitureFactory;
import AbstractFactory.ModernFurnitureFactoryImpl;
import AbstractFactory.Products.Chair;
import AbstractFactory.Products.Table;
import AbstractFactory.VictorianFurnitureFactoryImpl;
import Factory.Shape;
import Factory.ShapeFactory;

public class Main {
    public static void main(String[] args) {
        // Factory Example
        ShapeFactory factory = new ShapeFactory();
        Shape shape = factory.getShape("Circle");
        shape.draw();
        shape = factory.getShape("Square");
        shape.draw();
        shape = factory.getShape("Rectangle");
        if(shape == null) System.out.println("No such shape");

        // Abstract Factory Example
        FurnitureFactory furnitureFactory = new ModernFurnitureFactoryImpl();
        Chair chair = furnitureFactory.createChair();
        Table table = furnitureFactory.createTable();
        chair.sit();
        table.use();

        furnitureFactory = new VictorianFurnitureFactoryImpl();
        chair = furnitureFactory.createChair();
        table = furnitureFactory.createTable();
        chair.sit();
        table.use();
    }
}