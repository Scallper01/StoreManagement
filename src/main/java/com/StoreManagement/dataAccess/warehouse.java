package com.StoreManagement.dataAccess;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name="WAREHOUSES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class warehouse {
    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    @JoinColumn(name="ID_INVENTORY")
    private Collection<inventory> inventories;
}
