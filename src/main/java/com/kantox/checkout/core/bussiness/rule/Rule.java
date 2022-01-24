package com.kantox.checkout.core.bussiness.rule;

@FunctionalInterface
public interface Rule {

    Double finalRate(Long quantity, Double originalPrice);
}
