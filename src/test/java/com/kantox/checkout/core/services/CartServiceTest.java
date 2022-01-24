package com.kantox.checkout.core.services;

import com.kantox.checkout.core.converter.CartConverter;
import com.kantox.checkout.core.exceptions.DataNotFoundException;
import com.kantox.checkout.core.model.Cart;
import com.kantox.checkout.core.model.Item;
import com.kantox.checkout.entrypoints.dto.CartDto;
import com.kantox.checkout.entrypoints.dto.ItemDto;
import com.kantox.checkout.repositories.CartRepository;
import com.kantox.checkout.repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Autowired
    @InjectMocks
    CartService cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private CartConverter cartConverter;

    @Test
    public void testCartServiceWithExistingItemShouldReturnOk() throws Exception {
        // given
        Long cartId = 1L;
        String code = "GR1";
        Cart cart = new Cart(cartId);
        ItemDto itemDto = new ItemDto(code);
        given(itemRepository.findByCode(code))
                .willReturn(new Item(code));
        given(cartRepository.existsById(cartId))
                .willReturn(false);
        given(cartRepository.save(any()))
                .willReturn(cart);
        given(cartConverter.convertToDto(cart))
                .willReturn(new CartDto(Arrays.asList(itemDto)));

        // when
        CartDto result = cartService.addItemToCart(cartId, itemDto);

        // then
        assertEquals(1,result.getItems().size());
    }

    @Test
    public void testCartServiceWithAnNonExistingItemShouldThrowDataNotFoundException() {
        // given
        Long cartId = 1L;
        String code = "GR1";
        Item item = new Item(code);
        Cart cart = new Cart(cartId);
        List<Item> items = Arrays.asList(item);
        cart.setItems(items);
        ItemDto itemDto = new ItemDto(code);
        given(itemRepository.findByCode(code))
                .willReturn(null);

        // then
        assertThrows(DataNotFoundException.class, () -> {
            cartService.addItemToCart(cartId, itemDto);});
    }
}
