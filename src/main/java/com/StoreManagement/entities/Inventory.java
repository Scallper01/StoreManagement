package com.StoreManagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "INVENTORIES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="ID_PRODUCT")
    private Product product;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "ID_WAREHOUSE")
    private Warehouse warehouse;

}
