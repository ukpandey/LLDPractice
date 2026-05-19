import Base.BasePizza;
import Base.MargheritaConcrete;
import Decorators.ExtraCheeseConcrete;
import Decorators.MushroomsConcrete;

public class Main {
    public static void main(String[] args) {
        // Margherita
        BasePizza pizza1 = new MargheritaConcrete();
        System.out.println(pizza1.cost());
        // Margherita + cheese
        BasePizza pizza2 = new ExtraCheeseConcrete(pizza1);
        System.out.println(pizza2.cost());
        // Margherita + cheese + mushroom
        BasePizza pizza3 = new MushroomsConcrete(pizza2);
        System.out.println(pizza3.cost());
    }
}