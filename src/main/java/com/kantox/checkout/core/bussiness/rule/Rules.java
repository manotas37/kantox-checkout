package com.kantox.checkout.core.bussiness.rule;

public class Rules {

    /**
     * All the possible rules that can be applied to product. This also can be done by implement the
     * Rule interface for each rule and create a given class.
     */
    public final Rule defaultRule = (a, b) -> b *a;
    public final Rule ruleOneFree = (a, b) -> b * Math.ceil(a.doubleValue() / 2D);
    public final Rule ruleDiscountByItem = (a, b) -> {
        Double price = b;
        if (a > 2) price = b*0.9;
        return price * a;
    };
    public final Rule ruleDiscountByTotal = (a, b) ->{
        Double price = a*b;
        if (a > 2) price = price*2/3;
        return price;
    };

}
