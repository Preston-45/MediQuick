package com.tibuhealth.Tibu.service;


import com.tibuhealth.Tibu.exception.ResourceNotFoundException;
import com.tibuhealth.Tibu.model.Order;
import com.tibuhealth.Tibu.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    //create a method that returns a list of orders
    public List<Order> getAllOrders(){
        //create a List
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    //get an order
    public Optional<Order> getOrder(Long id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        // check if optional orders contains a value, and return  if it is present
        if (optionalOrder.isPresent()){
            return Optional.of(optionalOrder.get());
        }else  {
            // Handle the case where the order with the given ID was not found
            // here we could throw an exceptional or return nulll
            return null;
        }
    }

    // Add an Order
    public Order addOrder(Order order){
        orderRepository.save(order);
        return order;
    }

    // update an Order
    public void updateOrder(Long id, Order updatedOrder){
        // Retrieve the existing order from the database by ID
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()){
            //Get the existing order
            Order existingOrder = optionalOrder.get();
            // update the properties of the existing orders with values from the updated orders
            existingOrder.setOrder_date(updatedOrder.getOrder_date());
            existingOrder.setCustomer(updatedOrder.getCustomer());
            existingOrder.setStatus(updatedOrder.getStatus());
            // Save the updated order to the database
            orderRepository.save(existingOrder);
        }else {
            // Handle the case where the order with the given ID was not found
            throw  new ResourceNotFoundException("Order not found with id:" + id);
        }
    }

    public Order deleteOrder(Order id){
        orderRepository.delete(id);
        return id;
    }
}
