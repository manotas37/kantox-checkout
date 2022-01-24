package com.kantox.checkout.core.services;

import com.kantox.checkout.core.converter.ItemConverter;
import com.kantox.checkout.entrypoints.dto.CheckoutDto;
import com.kantox.checkout.core.exceptions.DataNotFoundException;
import com.kantox.checkout.core.model.Cart;
import com.kantox.checkout.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

@Service
public class CheckoutService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemConverter itemConverter;

    @Autowired
    ResolveAndApplyRuleService resolveAndApplyRuleService;

    public CheckoutDto calculatePrice(Long cartId) throws DataNotFoundException {
        try {
            Cart cart = cartRepository.getById(cartId);
            Double total = resolveAndApplyRuleService.resolveAndApplyRule(cart);
            CheckoutDto checkoutDto = new CheckoutDto();
            checkoutDto.setTotalPrice(total);
            checkoutDto.setItems(itemConverter.convertToDtos(cart));
            return checkoutDto;
        }catch (EntityNotFoundException entityNotFoundException){
            throw new DataNotFoundException(Cart.class.getSimpleName(), cartId.toString());
        }
    }

}
