package com.tibuhealth.Tibu.controller;

import com.tibuhealth.Tibu.exception.ResourceNotFoundException;
import com.tibuhealth.Tibu.model.Order;
import com.tibuhealth.Tibu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class OrderController {

    @Autowired // declares this as a dependency
    private OrderService orderService;

    @RequestMapping("/api/v1/order")
    public List<Order> getAllOrders(){return  orderService.getAllOrders();}


    @PostMapping("/api/v1/orders")
    public Order addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    @GetMapping("/api/v1/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
        Order order = orderService.getOrder(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order does not exist with id:" + id));
        return ResponseEntity.ok(order);
    }

    // build update order REST API

    @PutMapping("/api/v1/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable long id, @RequestBody Order orderdetails){
        try {
            // call the updatedOrders method from the service class, passing the ID and updated order details
            orderService.updateOrder(id,orderdetails);
            // if update was successful, return 200 OK response with the updated customer object
            return ResponseEntity.ok(orderdetails);
        } catch (ResourceNotFoundException e) {
            // if the order with the given ID ws not found, return a 404 Not Found Response
            return ResponseEntity.notFound().build();
        }
    }

    //build delete Order REST API
    @DeleteMapping("/api/v1/orders/{id}")
    public ResponseEntity<HttpEntity> deletedOrder(@PathVariable Order id) {
        Order order = orderService.deleteOrder(id);
        // delete order details from the database
        orderService.deleteOrder(order);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
