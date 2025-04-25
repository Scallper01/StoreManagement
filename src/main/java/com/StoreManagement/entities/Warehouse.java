package com.StoreManagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="WAREHOUSES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse implements Serializable {
    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany (mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Inventory> inventories;
}
