package com.tibuhealth.Tibu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customer_id;

    @Column(name = "name")
    private String  name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private Integer phone_number;

    @Column(name = "address")
    private  String address;
}
