package gpulenta.quipu.shoppingCart.service;

import gpulenta.quipu.shoppingCart.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart findByUserId(Long userId);

    ShoppingCart findById(Long id);

    ShoppingCart save(ShoppingCart shoppingCart);

    ShoppingCart update(ShoppingCart shoppingCart);

    void delete(Long id);

    List<ShoppingCart> findAll();
}
