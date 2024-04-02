package com.tibuhealth.Tibu.service;

import com.tibuhealth.Tibu.exception.ResourceNotFoundException;
import com.tibuhealth.Tibu.model.Customer;
import com.tibuhealth.Tibu.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    //create a meethod that returns a list of Customer
    public List<Customer> getAllCustomers(){
        // creating a List
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    //get a customer
    public Optional<Customer> getCustomer(Long id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        // Check if optional customer contains a value, and return it if present
        if (optionalCustomer.isPresent()){
            return Optional.of(optionalCustomer.get());
        }else {
            //Handle the case where the customer with the given ID was not found
            // here we could throw an exceptional or return null
            return null;
        }

    }

    // Add a Customer
    public Customer addCustomer(Customer customer){
        customerRepository.save(customer);
        return customer;
    }

    /*
    Loop through every customer depending on the size of the customer for each customer in the list compare with the id
    if it matches update it
     */

    // Update a customer by ID
    public void updateCustomer(Long id, Customer updatedCustomer) {
        // Retrieve the existing customer from the database by ID
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            // Get the existing customer
            Customer existingCustomer = optionalCustomer.get();
            // Update the properties of the existing customer with values from the updated customer
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            existingCustomer.setPhone_number(updatedCustomer.getPhone_number());
            // Save the updated customer to the database
            customerRepository.save(existingCustomer);
        } else {
            // Handle the case where the customer with the given ID was not found
            throw new ResourceNotFoundException("Customer not found with id: " + id);
        }
    }


    public Customer deleteCustomer(Customer id){
        customerRepository.delete(id);
        return id;
    }









}
