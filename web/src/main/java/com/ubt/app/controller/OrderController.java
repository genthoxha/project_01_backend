package com.ubt.app.controller;
import com.ubt.app.util.Utils;
import com.ubt.model.Driver;
import com.ubt.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.ubt.service.OrderService;

import java.util.List;

@RestController ("/orders")
public class OrderController {
    public static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    // Get all users
    @GetMapping("")
    public ResponseEntity<List<Order>> listAllOrders() {
        logger.info("List all orders");
        List<Order> orders = orderService.getAll();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable ("id") int id) {
        logger.info("Get order with id: "+id);
        // service + repository help web to provide data from database
        Order order = orderService.getById(id);
        if (order == null) {
            logger.error("order with id:"+id+" doesnt exist.");
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    // create a driver
    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody Order order, UriComponentsBuilder uriCBuilder) {
        logger.info("Creating order: {}", order);

        if (orderService.getById(order.getId()) != null) {
            logger.error("order with id:"+ order.getId()+" already exist.");
            return new ResponseEntity<>(new Utils
                    ("Unable to create order with id:" + order.getId() + " exist."),
                    HttpStatus.CONFLICT);
        }
        orderService.save(order);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriCBuilder.path("/api/order/{id}").buildAndExpand(order.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") int id, @RequestBody Order order) {
        logger.info("Updating order with id {}", id);
        Order currentOrder = orderService.getById(id);

        if (currentOrder == null) {
            logger.error("Unable to update. Order with id {} not found.", id);
            return new ResponseEntity<>(new Utils("Unable to update. Order with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentOrder.setAmount(order.getAmount());
        currentOrder.setIsPayed(order.getIsPayed());
        currentOrder.setOrderAddressDetail(order.getOrderAddressDetail());
        return new ResponseEntity<>(currentOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") int id) {
        logger.info("Fetching & Deleting order with id {}", id);
        Order order = orderService.getById(id);
        if (order == null) {
            logger.error("Unable to delete. Order with id {} not found.", id);
            return new ResponseEntity<>(new Utils("Unable to delete. Order with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        orderService.deleteById(id);
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }
}