package com.kantox.checkout.entrypoints.controller;

import com.kantox.checkout.core.exceptions.DataNotFoundException;
import com.kantox.checkout.core.model.Cart;
import com.kantox.checkout.core.services.CheckoutService;
import com.kantox.checkout.entrypoints.dto.CheckoutDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.kantox.checkout.entrypoints.routes.Routes.CHECKOUT_BASE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.util.Strings.concat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CheckoutController.class)
public class CheckoutControllerTest {

    @MockBean
    private CheckoutService checkoutService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testCheckoutWithAExistentCartShouldReturnOk() throws Exception {
        // given
        Long cartId = 1L;
        given(checkoutService.calculatePrice(cartId))
                .willReturn(new CheckoutDto());

        // when
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.
                get(concat(CHECKOUT_BASE,"/",cartId)).accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void testCheckoutWithAnNonExistentCartShouldReturnNotFound() throws Exception {
        // given
        Long cartId = 1L;
        given(checkoutService.calculatePrice(cartId))
                .willThrow(new DataNotFoundException(Cart.class.getSimpleName(),"1"));

        // when
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.
                        get(concat(CHECKOUT_BASE,"/",cartId)).accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

}
