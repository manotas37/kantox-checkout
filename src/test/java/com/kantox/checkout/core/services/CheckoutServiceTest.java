package com.kantox.checkout.core.services;

import com.kantox.checkout.core.converter.ItemConverter;
import com.kantox.checkout.core.exceptions.DataNotFoundException;
import com.kantox.checkout.core.model.Cart;
import com.kantox.checkout.entrypoints.dto.CheckoutDto;
import com.kantox.checkout.entrypoints.dto.ItemDto;
import com.kantox.checkout.repositories.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CheckoutServiceTest {

    @Autowired
    @InjectMocks
    CheckoutService checkoutService;

    @Mock
    CartRepository cartRepository;

    @Mock
    ItemConverter itemConverter;

    @Mock
    ResolveAndApplyRuleService resolveAndApplyRuleService;

    @Test
    public void testCheckoutServiceWithExistingCartShouldReturnOk() throws Exception {
        // given
        Long cartId = 1L;
        String code = "GR1";
        ItemDto itemDto = new ItemDto(code);
        Cart cart = new Cart(cartId);
        List<ItemDto> itemsDtos =Arrays.asList(itemDto,itemDto,itemDto);
        given(cartRepository.getById(cartId))
                .willReturn(cart);
        given(resolveAndApplyRuleService.resolveAndApplyRule(any()))
                .willReturn(6.22);
        given(itemConverter.convertToDtos(any()))
                .willReturn(itemsDtos);

        // when
        CheckoutDto result = checkoutService.calculatePrice(cartId);

        // then
        assertEquals(6.22,result.getTotalPrice());
    }

    @Test
    public void testCheckoutServiceWithNonExistingCartShouldThrowDataException(){
        // given
        Long cartId = 1L;
        given(cartRepository.getById(cartId))
                .willThrow(new EntityNotFoundException());

        // Then
        assertThrows(DataNotFoundException.class, () -> {
            checkoutService.calculatePrice(cartId);});
    }
}
