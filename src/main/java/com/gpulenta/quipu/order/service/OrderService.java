package com.gpulenta.quipu.order.service;

import com.gpulenta.quipu.order.model.Order;
import com.gpulenta.quipu.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(int id, Order order) {
        Order orderToUpdate = orderRepository.findById(id).orElse(null);
        if (orderToUpdate != null) {
            orderToUpdate.setOrderDate(order.getOrderDate());
            orderToUpdate.setWaitingTime(order.getWaitingTime());
            orderToUpdate.setOrderStatus(order.getOrderStatus());
            orderToUpdate.setTotalPrice(order.getTotalPrice());
            orderToUpdate.setPaymentMethod(order.getPaymentMethod());
            orderToUpdate.setPaymentAmount(order.getPaymentAmount());
            orderToUpdate.setCartItems(order.getCartItems());
            return orderRepository.save(orderToUpdate);
        } else {
            return null;
        }
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
