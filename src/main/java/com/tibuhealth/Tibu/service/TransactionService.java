package com.tibuhealth.Tibu.service;

import com.tibuhealth.Tibu.exception.ResourceNotFoundException;
import com.tibuhealth.Tibu.model.Transaction;
import com.tibuhealth.Tibu.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    //create a method that returns a list of Transaction
    public List<Transaction> getAllTransaction(){
        // creating a List
        List<Transaction> transactions = new ArrayList<>();
        transactionRepository.findAll().forEach(transactions::add);
        return transactions;
    }

    //get a transaction
    public Optional<Transaction> getTransaction(Long id){
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        // check if optional transaction contains a value, and return it if present
        if(optionalTransaction.isPresent()){
            return  Optional.of(optionalTransaction.get());
        }else {
            //Handle the case where the customer with the given ID was not found
            // here we could throw an exceptional or return null
            return null;
        }
    }

    // Add a Transaction
    public  Transaction addTransaction(Transaction transaction){
        transactionRepository.save(transaction);
        return transaction;
    }

    /*
    Loop through every transaction depending on the size of the transaction for each transaction in the list compare with the id
    if it matches update it
     */

    //update a transaction by ID
    public void updateTransaction(Long id, Transaction updatedTransaction){
        //Retrieve the existing transaction from the database by ID
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isPresent()){
            // Get the existing transaction
            Transaction existingTransaction = optionalTransaction.get();
            existingTransaction.setTransaction_date(updatedTransaction.getTransaction_date());
            existingTransaction.setOrder(updatedTransaction.getOrder());
            existingTransaction.setAmount(updatedTransaction.getAmount());
            existingTransaction.setPayment_status(updatedTransaction.getPayment_status());
            //update the properties of the existing transaction with values from the updated transaction

        } else {
            throw  new ResourceNotFoundException("Transaction not found with id" + id);
        }
    }

    public Transaction deleteTransaction(Transaction id){
        transactionRepository.delete(id);
        return id;
    }
}
