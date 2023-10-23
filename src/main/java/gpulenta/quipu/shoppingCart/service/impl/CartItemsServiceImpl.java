package gpulenta.quipu.shoppingCart.service.impl;


import gpulenta.quipu.product.repository.ProductRepository;
import gpulenta.quipu.shoppingCart.model.CartItems;
import gpulenta.quipu.shoppingCart.repository.CartItemsRepository;
import gpulenta.quipu.shoppingCart.service.CartItemsService;
import gpulenta.quipu.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartItemsServiceImpl implements CartItemsService {
    private final CartItemsRepository cartItemsRepository;
    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    @Autowired
    public CartItemsServiceImpl(CartItemsRepository cartItemsRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartItemsRepository = cartItemsRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<CartItems> getAllCartItems() {
        List<CartItems> cartItems = cartItemsRepository.findAll();
        for (CartItems cartItem : cartItems) {
            productRepository.findById(cartItem.getProductId()).ifPresent(cartItem::setProduct);
        }
        return cartItems;
    }

    @Override
    public CartItems save(CartItems cartItems) {
        return cartItemsRepository.save(cartItems);
    }

    @Override
    public CartItems update(CartItems cartItems) {
        return null;
    }

    @Override
    public void delete(Long id) {
        cartItemsRepository.deleteById(id);
    }

    @Override
    public List<CartItems> findByShoppingCart_Id(Long id) {

        List<CartItems> cartItems = cartItemsRepository.findByShoppingCart_Id(id);
        for (CartItems cartItem : cartItems) {
            productRepository.findById(cartItem.getProductId()).ifPresent(cartItem::setProduct);
        }
        return cartItems;

    }

    @Override
    public CartItems findById(Long id) {
        return cartItemsRepository.findById(id).orElse(null);
    }

}
