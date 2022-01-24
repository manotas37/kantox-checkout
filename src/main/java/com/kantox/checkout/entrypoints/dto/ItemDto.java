package com.kantox.checkout.entrypoints.dto;

public class ItemDto {
    private String productCode;

    public ItemDto(String productCode) {
        this.productCode = productCode;
    }

    public ItemDto() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
