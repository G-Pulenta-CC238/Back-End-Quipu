package gpulenta.quipu.shoppingCart.repository;

import gpulenta.quipu.shoppingCart.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    List<CartItems> findByShoppingCart_Id(Long id);


}