package gpulenta.quipu.shoppingCart.service;

import gpulenta.quipu.shoppingCart.model.CartItems;

import java.util.List;

public interface CartItemsService {
    List<CartItems> getAllCartItems();

    CartItems save(CartItems cartItems);

    CartItems update(CartItems cartItems);

    void delete(Long id);

    List<CartItems> findByShoppingCart_Id(Long id);

    CartItems findById(Long id);

}
