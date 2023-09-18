package com.gpulenta.quipu.cartItem.repository;

import com.gpulenta.quipu.cartItem.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {
    List<CartItems> findByShoppingCart_Id(int id);
}