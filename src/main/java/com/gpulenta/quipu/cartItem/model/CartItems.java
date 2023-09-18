package com.gpulenta.quipu.cartItem.model;

import com.gpulenta.quipu.products.model.Product;
import com.gpulenta.quipu.shoppingCart.model.ShoppingCart;
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
@Table(name="cart_items")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantity", length = 50)
    private int productQuantity;

    @Column(name = "subtotal", length = 50)
    private double cartSubtotal;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_shopping_cart", nullable = false)
    private ShoppingCart shoppingCart;

}
