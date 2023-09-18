package com.gpulenta.quipu.cartItem.service;

import com.gpulenta.quipu.cartItem.model.CartItems;
import com.gpulenta.quipu.cartItem.repository.CartItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemsService{
    private final CartItemsRepository cartItemsRepository;

    @Autowired
    public CartItemsService(CartItemsRepository cartItemsRepository) {
        this.cartItemsRepository = cartItemsRepository;
    }

    public List<CartItems> getAllCartItems() {
        return cartItemsRepository.findAll();
    }

    public CartItems getCartItemsById(int id) {
        return cartItemsRepository.findById(id).orElse(null);
    }

    public CartItems saveCartItems(CartItems cartItems) {
        return cartItemsRepository.save(cartItems);
    }

    public CartItems updateCartItems(int id, CartItems cartItems) {
        CartItems cartItemsToUpdate = cartItemsRepository.findById(id).orElse(null);
        if (cartItemsToUpdate != null) {
            cartItemsToUpdate.setProductQuantity(cartItems.getProductQuantity());
            cartItemsToUpdate.setCartSubtotal(cartItems.getCartSubtotal());
            cartItemsToUpdate.setProduct(cartItems.getProduct());
            cartItemsToUpdate.setShoppingCart(cartItems.getShoppingCart());
            return cartItemsRepository.save(cartItemsToUpdate);
        } else {
            return null;
        }
    }

    public void deleteCartItems(int id) {
        cartItemsRepository.deleteById(id);
    }

    public List<CartItems> getCartItemsByShoppingCartId(int id) {
        return cartItemsRepository.findByShoppingCart_Id(id);
    }
}