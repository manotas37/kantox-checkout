package com.kantox.checkout.entrypoints.dto;

import java.util.List;

public class CheckoutDto {
    private List<ItemDto> items;
    private Double totalPrice;

    public CheckoutDto() {
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
