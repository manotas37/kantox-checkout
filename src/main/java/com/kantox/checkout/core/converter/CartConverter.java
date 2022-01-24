package com.kantox.checkout.core.converter;

import com.kantox.checkout.entrypoints.dto.CartDto;
import com.kantox.checkout.core.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartConverter {

    @Autowired
    ItemConverter itemConverter;

    public CartDto convertToDto(Cart cart){
        return new CartDto(itemConverter.convertToDtos(cart));
    }

}
