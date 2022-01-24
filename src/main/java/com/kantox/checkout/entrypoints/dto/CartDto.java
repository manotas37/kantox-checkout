package com.kantox.checkout.entrypoints.dto;

import java.util.List;

public class CartDto {
    private List<ItemDto> items;

    public CartDto() {
    }

    public CartDto(List<ItemDto> items) {
        this.items = items;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}
