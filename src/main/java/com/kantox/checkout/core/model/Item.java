package com.kantox.checkout.core.model;

import javax.persistence.*;

@Entity
@Table(name = "Item")
public class Item {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    public Item() {
    }

    public Item(String code) {
        this.code = code;
    }

    public Item(String code, Double price) {
        this.code = code;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
