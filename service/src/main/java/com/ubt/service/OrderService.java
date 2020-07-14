package com.ubt.service;

import com.ubt.model.Order;
import com.ubt.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(int id) {
        return orderRepository.findById(id);
    }

    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }

    public void save(Order u) {
        Order order = new Order();

        order.setAmount(u.getAmount());
        order.setIsPayed(u.getIsPayed());
        order.setOrderAddressDetail(u.getOrderAddressDetail());
        orderRepository.save(order);
    }
}
