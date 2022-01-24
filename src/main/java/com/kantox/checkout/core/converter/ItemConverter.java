package com.kantox.checkout.core.converter;

import com.kantox.checkout.core.model.Cart;
import com.kantox.checkout.entrypoints.dto.ItemDto;
import com.kantox.checkout.core.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemConverter {

    public ItemDto convertToDto(Item item){
        ItemDto itemDto= new ItemDto(item.getCode());
        return itemDto;
    }

    public List<ItemDto>  convertToDtos(Cart cart){
        return cart.getItems().stream().map( item -> this.convertToDto(item)).collect(Collectors.toList());
    }
}
