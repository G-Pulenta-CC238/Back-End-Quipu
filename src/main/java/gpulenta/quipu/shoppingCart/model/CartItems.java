package gpulenta.quipu.shoppingCart.model;

import gpulenta.quipu.product.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carts_items")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", length = 50)
    private int productQuantity;

    @Column(name = "subtotal", length = 50)
    private double cartSubtotal;

    @JoinColumn(name = "id_product", nullable = false)
    private long productId;

    @Transient
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_shopping_cart", nullable = false)
    private ShoppingCart shoppingCart;
}
