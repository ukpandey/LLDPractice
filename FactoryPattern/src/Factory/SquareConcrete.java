package Factory;

public class SquareConcrete implements Shape{
    @Override
    public void draw() {
        System.out.println("Drawing Square");
    }
}
