package com.kantox.checkout.core.converter;

import com.kantox.checkout.core.model.Cart;
import com.kantox.checkout.core.model.Item;
import com.kantox.checkout.entrypoints.dto.ItemDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemConverterTest {

    ItemConverter itemConverter = new ItemConverter();

    @Test
    public void testItemConverterToDto() {
        // given
        Item item = new Item("GR1",3.11);

        // when
        ItemDto itemDto = itemConverter.convertToDto(item);

        // then
        assertEquals(item.getCode(),itemDto.getProductCode());
    }

    @Test
    public void testItemConverterToDtos() {
        // given
        Item item = new Item("GR1",3.11);
        Item itemCf = new Item("CF1",3.11);
        Cart cart = new Cart();
        cart.setItems(Arrays.asList(item,itemCf));

        // when
        List<ItemDto> itemDtos = itemConverter.convertToDtos(cart);

        // then
        assertEquals(cart.getItems().size(),itemDtos.size());
    }
}
