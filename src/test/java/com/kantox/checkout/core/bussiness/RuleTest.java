package com.kantox.checkout.core.bussiness;

import com.kantox.checkout.core.bussiness.rule.Rules;
import com.kantox.checkout.core.util.Rounding;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RuleTest {

    Rules rules = new Rules();

    @Test
    void testRuleOneFreeWith3RepeatedShouldApplyOnce() {
        assertEquals(6.22D, rules.ruleOneFree.finalRate(3L,3.11));
    }

    @Test
    void testRuleOneFreeWith2RepeatedShouldApplyOnce() {
        assertEquals(3.11D, rules.ruleOneFree.finalRate(2L,3.11));
    }

    @Test
    void testRuleDiscountByItemWith3RepeatedShouldApplyOnce() {
        assertEquals(13.5D, rules.ruleDiscountByItem.finalRate(3L,5.00));
    }

    @Test
    void testRuleDiscountByTotalWith3RepeatedShouldApplyOnce() {
        assertEquals(22.46D, Rounding.roundToScale(rules.ruleDiscountByTotal.finalRate(3L,11.23),2));
    }

    @Test
    void testDefaultRule() {
        assertEquals(15.00D, rules.defaultRule.finalRate(3L,5.00));
    }
}
