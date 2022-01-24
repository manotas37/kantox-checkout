package com.kantox.checkout.entrypoints.controller;

import com.google.gson.Gson;
import com.kantox.checkout.entrypoints.dto.CartDto;
import com.kantox.checkout.entrypoints.dto.ItemDto;
import com.kantox.checkout.core.exceptions.DataNotFoundException;
import com.kantox.checkout.core.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.kantox.checkout.entrypoints.routes.Routes.CART_BASE;
import static com.kantox.checkout.entrypoints.routes.Routes.CART_ID_PARAM;

@RestController
@RequestMapping(CART_BASE)
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    Gson gson;

    @PostMapping( value = CART_ID_PARAM, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addItemToCart(@PathVariable Long cartId, @RequestBody ItemDto itemDto) {
        CartDto cartDto;
        try {
            cartDto = cartService.addItemToCart(cartId,itemDto);
        } catch (DataNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gson.toJson(errorResponse));
        }
        return new ResponseEntity<>(gson.toJson(cartDto), HttpStatus.OK);
    }
}
