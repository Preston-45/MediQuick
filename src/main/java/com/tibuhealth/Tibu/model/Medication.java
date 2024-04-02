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
@Table(name = "medication")
@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medication_id;

    @Column(name = "name")
    private  String name;

    @Column(name = "description")
    private  String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "stock_quantity")
    private  Integer stock_quantity;
}
