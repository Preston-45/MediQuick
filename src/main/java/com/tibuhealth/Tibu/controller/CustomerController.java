package com.tibuhealth.Tibu.controller;

import com.tibuhealth.Tibu.exception.ResourceNotFoundException;
import com.tibuhealth.Tibu.model.Customer;
import com.tibuhealth.Tibu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class CustomerController {

    @Autowired  //declares this as a dependency
    private CustomerService customerService;


    @RequestMapping("/api/v1/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/api/v1/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);

    }

    //build get customer by id REST API
    @GetMapping("/api/v1/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
        Customer customer = customerService.getCustomer(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer does not exist with id:" + id));
        return ResponseEntity.ok(customer);
    }

    //build update customer REST API
    @PutMapping("/api/v1/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody Customer customerdetails) {
        try {
            //call the updatedCustomer method from the service class, passing the ID and updated customer details
            customerService.updateCustomer(id,customerdetails);
            // if update was successful, return 200 OK response with the updated customer object
            return ResponseEntity.ok(customerdetails);
        } catch (ResourceNotFoundException e) {
            // if the customer with the given ID was not found, return a 404 Not Found Response
            return ResponseEntity.notFound().build();
        }

    }

    //build delete Customer REST API
    @DeleteMapping("/api/v1/customers/{id}")
    public ResponseEntity<HttpEntity> deletedCustomer(@PathVariable Customer id) {
        Customer customer = customerService.deleteCustomer(id);
        //delete customer details frm the database
        customerService.deleteCustomer(customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
