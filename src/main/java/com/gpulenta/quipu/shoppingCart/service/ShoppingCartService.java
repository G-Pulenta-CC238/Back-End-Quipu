package com.gpulenta.quipu.shoppingCart.service;

import com.gpulenta.quipu.shoppingCart.model.ShoppingCart;
import com.gpulenta.quipu.shoppingCart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    public ShoppingCart getShoppingCartById(int id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }

    public ShoppingCart getShoppingCartByUserId(int id) {
        return shoppingCartRepository.findByUser_Id(id);
    }

    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public void deleteShoppingCart(int id) {
        shoppingCartRepository.deleteById(id);
    }

    public boolean existsByUser_Id(int id) {
        return shoppingCartRepository.existsByUser_Id(id);
    }
}
