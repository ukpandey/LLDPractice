package Decorators;

import Base.BasePizza;

public class ExtraCheeseConcrete extends ToppingsDecorator{
    BasePizza basePizza;
    public ExtraCheeseConcrete(BasePizza basePizza){
        this.basePizza = basePizza;
    }
    @Override
    public int cost() {
        return basePizza.cost() + 20;
    }
}
