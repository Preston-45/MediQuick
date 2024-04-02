package com.tibuhealth.Tibu.repository;

import com.tibuhealth.Tibu.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
    //Accepts all crud databases method
}
