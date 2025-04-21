package com.StoreManagement.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "INVENTORIES")

public class Inventory implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="ID_WAREHOUSE")
    private Warehouse warehouse;
    @OneToOne
    @JoinColumn(name="ID_PRODUCT")
    private Product product;
    private Integer quantity;

}
