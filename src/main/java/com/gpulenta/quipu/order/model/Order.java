package com.gpulenta.quipu.order.model;

import com.gpulenta.quipu.cartItem.model.CartItems;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_date", nullable = false, length = 50)
    private LocalDate orderDate;

    @Column(name = "waiting_time", nullable = false, length = 50)
    private String waitingTime;

    @Column(name = "total_price", nullable = false, length = 50)
    private Double totalPrice;

    @Column(name = "order_status", nullable = false, length = 50)
    private String orderStatus;

    @Column(name = "payment_method", nullable = false, length = 50)
    private String paymentMethod;

    @Column(name = "payment_amount", nullable = false, length = 50)
    private Double paymentAmount;

    @ManyToOne
    @JoinColumn(name = "id_cart_items", nullable = false)
    private CartItems cartItems;
}
