package com.kantox.checkout.core.services;

import com.kantox.checkout.core.converter.CartConverter;
import com.kantox.checkout.entrypoints.dto.CartDto;
import com.kantox.checkout.entrypoints.dto.ItemDto;
import com.kantox.checkout.core.exceptions.DataNotFoundException;
import com.kantox.checkout.core.model.Cart;
import com.kantox.checkout.core.model.Item;
import com.kantox.checkout.repositories.CartRepository;
import com.kantox.checkout.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CartConverter cartConverter;

    public CartDto addItemToCart(Long cartId, ItemDto itemDto) throws DataNotFoundException {
        Item item = itemRepository.findByCode(itemDto.getProductCode());
        if (item == null)
                throw new DataNotFoundException(Item.class.getSimpleName(),itemDto.getProductCode());
        Cart cart = cartRepository.existsById(cartId) ? cartRepository.getById(cartId): new Cart(cartId);
        cart.getItems().add(item);
        return cartConverter.convertToDto(cartRepository.save(cart));
    }
}