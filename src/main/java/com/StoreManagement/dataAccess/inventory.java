package com.StoreManagement.dataAccess;

import jakarta.persistence.*;
import org.hibernate.mapping.ToOne;

@Entity
@Table(name = "INVENTORIES")

public class inventory {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="ID_WAREHOUSE")
    private warehouse warehouse;
    @OneToOne
    @JoinColumn(name="ID_PRODUCT")
    private product product;
    private Integer quantity;

}
