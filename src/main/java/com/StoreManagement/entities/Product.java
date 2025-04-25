package com.StoreManagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="PRODUCTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private Double price;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_SUPPLIER")
    private Supplier supplier;
}
