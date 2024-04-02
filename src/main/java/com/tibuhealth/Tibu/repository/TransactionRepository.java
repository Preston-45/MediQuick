package com.tibuhealth.Tibu.repository;

import com.tibuhealth.Tibu.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    //Accepts all crud database methods
}
