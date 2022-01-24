package com.kantox.checkout.entrypoints.controller;

import com.google.gson.Gson;
import com.kantox.checkout.core.exceptions.DataNotFoundException;
import com.kantox.checkout.core.model.Item;
import com.kantox.checkout.core.services.CartService;
import com.kantox.checkout.entrypoints.dto.CartDto;
import com.kantox.checkout.entrypoints.dto.ItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.kantox.checkout.entrypoints.routes.Routes.CART_BASE;
import static com.kantox.checkout.entrypoints.routes.Routes.CART_ID_PARAM;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.util.Strings.concat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @MockBean
    private CartService cartService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    Gson gson;

    private MockMvc mockMvc;

    @Test
    public void testCheckoutWithAExistentItemShouldReturnOk() throws Exception {
        // given
        ItemDto itemDto = new ItemDto("GR1");
        Long cartId = 1L;
        given(cartService.addItemToCart(cartId, itemDto))
                .willReturn(new CartDto());

        // when
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.
                post(concat(CART_BASE,"/",cartId)).contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(itemDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    //@Test
    public void testCheckoutWithAnNonExistentItemShouldReturnNotFound() throws Exception {
        // given
        ItemDto itemDto = new ItemDto("DTO");
        Long cartId = 1L;
        given(cartService.addItemToCart(cartId,itemDto))
                .willThrow(new DataNotFoundException(Item.class.getSimpleName(),itemDto.getProductCode()));

        // Then
        mvc.perform(MockMvcRequestBuilders.
                        post(concat(CART_BASE,"/",cartId)).contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(itemDto))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                .andReturn().getResponse();

    }
}
