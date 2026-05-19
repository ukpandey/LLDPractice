package Decorators;

import Base.BasePizza;
import Base.MargheritaConcrete;

public class MushroomsConcrete extends ToppingsDecorator{
    BasePizza pizza;
    public MushroomsConcrete(BasePizza pizza){
        this.pizza = pizza;
    }

    @Override
    public int cost() {
        return pizza.cost() + 15;
    }
}
