package com.kantox.checkout.repositories;

import com.kantox.checkout.core.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
