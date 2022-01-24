package com.kantox.checkout.entrypoints.controller;

import com.google.gson.Gson;
import com.kantox.checkout.entrypoints.dto.CheckoutDto;
import com.kantox.checkout.core.exceptions.DataNotFoundException;
import com.kantox.checkout.core.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.kantox.checkout.entrypoints.routes.Routes.CART_ID_PARAM;
import static com.kantox.checkout.entrypoints.routes.Routes.CHECKOUT_BASE;


@RestController
@RequestMapping(CHECKOUT_BASE)
public class CheckoutController {

    @Autowired
    CheckoutService checkoutService;

    @Autowired
    Gson gson;

    @GetMapping( value = CART_ID_PARAM, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPrice(@PathVariable Long cartId) {
        CheckoutDto checkoutDto;
        try {
            checkoutDto = checkoutService.calculatePrice(cartId);
        } catch (DataNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gson.toJson(errorResponse));
        }
        return new ResponseEntity<>(gson.toJson(checkoutDto), HttpStatus.OK);
    }
}
