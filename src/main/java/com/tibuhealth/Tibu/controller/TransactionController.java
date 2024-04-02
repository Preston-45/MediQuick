package com.tibuhealth.Tibu.controller;


import com.tibuhealth.Tibu.exception.ResourceNotFoundException;
import com.tibuhealth.Tibu.model.Transaction;
import com.tibuhealth.Tibu.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class TransactionController {

    @Autowired //declares this as a dependency
    private TransactionService transactionService;

    @RequestMapping("/api/v1/transaction")
    public List<Transaction> getAllTransaction(){return transactionService.getAllTransaction();}

    @PostMapping("/api/v1/transaction")
    public Transaction addTransaction(@RequestBody Transaction transaction){
        return transactionService.addTransaction(transaction);
    }

    @GetMapping("/api/v1/transaction/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable long id){
        Transaction transaction = transactionService.getTransaction(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction does not exist with id:" + id));
        return ResponseEntity.ok(transaction);
    }

    //build update transaction REST API
    @PutMapping("/api/v1/transaction/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable long id, @RequestBody Transaction transactiondetails){
        try {
            transactionService.updateTransaction(id,transactiondetails);
            // if the transaction was successful, return 200 OK response with the updated transaction details
            return ResponseEntity.ok(transactiondetails);
        } catch (ResourceNotFoundException e){
            // if the transaction with the given ID was not found, return a 404 Not found Response
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/v1/transaction/{id}")
    public ResponseEntity<HttpEntity> deletedTransaction(@PathVariable Transaction id){
        Transaction transaction = transactionService.deleteTransaction(id);
        // delete transaction details frm the database
        transactionService.deleteTransaction(transaction);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
