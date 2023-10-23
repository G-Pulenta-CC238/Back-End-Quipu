package gpulenta.quipu.shoppingCart.service.impl;

import gpulenta.quipu.shoppingCart.model.ShoppingCart;
import gpulenta.quipu.shoppingCart.repository.ShoppingCartRepository;
import gpulenta.quipu.shoppingCart.service.ShoppingCartService;
import gpulenta.quipu.user.model.User;
import gpulenta.quipu.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    private final UserRepository userRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ShoppingCart findByUserId(Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
        if (shoppingCart != null) {
            shoppingCart.setUser(userRepository.findById(shoppingCart.getUserId()).orElse(null));
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCart findById(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElse(null);
        if (shoppingCart != null) {
            shoppingCart.setUser(userRepository.findById(shoppingCart.getUserId()).orElse(null));
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        ShoppingCart shoppingCartToUpdate = shoppingCartRepository.findById(shoppingCart.getId()).orElse(null);
        if (shoppingCartToUpdate != null) {
            shoppingCartToUpdate.setCartStatus(shoppingCart.getCartStatus());
            shoppingCartToUpdate.setUserId(shoppingCart.getUserId());
            return shoppingCartRepository.save(shoppingCartToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public List<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }
}
