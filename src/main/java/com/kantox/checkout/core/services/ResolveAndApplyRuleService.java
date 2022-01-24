package com.kantox.checkout.core.services;

import com.kantox.checkout.configuration.MapItemToRulesConfiguration;
import com.kantox.checkout.core.model.Cart;
import com.kantox.checkout.core.model.Item;
import com.kantox.checkout.core.util.Rounding;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ResolveAndApplyRuleService {

    MapItemToRulesConfiguration mapItemToRulesConfiguration;

    public ResolveAndApplyRuleService(MapItemToRulesConfiguration mapItemToRulesConfiguration) {
        this.mapItemToRulesConfiguration = mapItemToRulesConfiguration;
    }

    /**
     * This method receives the cart, group by product and count, the find the rule for the pricing and
     * applied return the total cost of the cart.
     * @param cart that need to calculate price.
     * @return the total price.
     */
    public Double resolveAndApplyRule(Cart cart){
        Map<Item, Long> productCount = cart.getItems().stream().collect(
                Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                )
        );
        return Rounding.roundToScale(productCount.keySet().stream()
                .mapToDouble(item -> mapItemToRulesConfiguration.findConfigRule(item.getCode())
                        .finalRate(productCount.get(item), item.getPrice())).sum(),2);

    }
}
