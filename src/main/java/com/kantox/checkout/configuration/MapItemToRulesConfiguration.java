package com.kantox.checkout.configuration;

import com.kantox.checkout.core.bussiness.ItemEnum;
import com.kantox.checkout.core.bussiness.rule.Rule;
import com.kantox.checkout.core.bussiness.rule.Rules;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MapItemToRulesConfiguration {

    private final Map<String,Rule> mapProductConfigs;

    /**
     * This method is the main configuration, map products to rules, if no rule is applied
     * should be only the default.
     */
    public MapItemToRulesConfiguration() {
        Rules rules = new Rules();
        mapProductConfigs = Map.of(
                ItemEnum.GR1.name(), rules.ruleOneFree,
                ItemEnum.SR1.name(), rules.ruleDiscountByItem,
                ItemEnum.CF1.name(),rules.ruleDiscountByTotal,
                ItemEnum.DEFAULT.name(), rules.defaultRule);
    }

    /**
     * @param  code of the product.
     * @return The rule for the given product code
     */
    public Rule findConfigRule(String code){
        return mapProductConfigs.containsKey(code)
                ? mapProductConfigs.get(code)
                : mapProductConfigs.get(ItemEnum.DEFAULT.name());
    }

}
