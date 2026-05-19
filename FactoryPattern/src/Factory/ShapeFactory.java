package Factory;

public class ShapeFactory {
    public Shape getShape(String shape){
        if(shape.equalsIgnoreCase("Circle"))
            return new CircleConcrete();
        else if(shape.equalsIgnoreCase("Square"))
            return new SquareConcrete();
        return null;
    }
}
