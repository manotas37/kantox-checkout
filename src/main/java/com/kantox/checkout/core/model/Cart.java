package com.kantox.checkout.core.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @Column(name = "id")
    private Long id;

    @ManyToMany
    private List<Item> items;

    public Cart() {
    }

    public Cart(Long id) {
        this.id = id;
        this.items = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
