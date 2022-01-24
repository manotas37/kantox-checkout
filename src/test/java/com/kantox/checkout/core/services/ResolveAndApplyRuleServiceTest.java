package com.kantox.checkout.core.services;

import com.kantox.checkout.configuration.MapItemToRulesConfiguration;
import com.kantox.checkout.core.model.Cart;
import com.kantox.checkout.core.model.Item;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ResolveAndApplyRuleServiceTest {

    ResolveAndApplyRuleService resolveAndApplyRuleService = new ResolveAndApplyRuleService(
            new MapItemToRulesConfiguration()
    );

    @Test
    void testRuleOneFreeWith3RepeatedShouldApplyOnce() {
        // given
        Cart cart = new Cart(1L);
        Item itemGr = new Item("GR1",3.11);
        Item itemSr = new Item("SR1",5.00);
        Item itemCf = new Item("CF1",11.23);

        List<Item> items = Arrays.asList(itemGr,itemSr,itemGr,itemGr,itemCf);
        cart.setItems(items);

        // then
        assertEquals(22.45, resolveAndApplyRuleService.resolveAndApplyRule(cart));
    }

    @Test
    void testRuleOneFreeWith2RepeatedShouldApplyOnce() {
        // given
        Cart cart = new Cart(1L);
        String code = "GR1";
        Item item = new Item(code,3.11);
        List<Item> items = Arrays.asList(item,item);
        cart.setItems(items);

        // then
        assertEquals(3.11, resolveAndApplyRuleService.resolveAndApplyRule(cart));
    }

    @Test
    void testRuleDiscountByProductWith3RepeatedShouldApplyOnce() {
        // given
        Cart cart = new Cart(1L);
        Item itemGr = new Item("GR1",3.11);
        Item itemSr = new Item("SR1",5.00);
        List<Item> items = Arrays.asList(itemSr,itemSr,itemGr,itemSr);
        cart.setItems(items);

        // then
        assertEquals(16.61, resolveAndApplyRuleService.resolveAndApplyRule(cart));
    }

    @Test
    void testRuleDiscountByTotalWith3RepeatedShouldApplyOnce() {
        // given
        Cart cart = new Cart(1L);
        Item itemGr = new Item("GR1",3.11);
        Item itemSr = new Item("SR1",5.00);
        Item itemCf = new Item("CF1",11.23);

        List<Item> items = Arrays.asList(itemGr,itemCf,itemSr,itemCf,itemCf);
        cart.setItems(items);

        // then
        assertEquals(30.57, resolveAndApplyRuleService.resolveAndApplyRule(cart));
    }
}
