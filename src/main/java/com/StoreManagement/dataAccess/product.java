package com.StoreManagement.dataAccess;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="PRODUCTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "ID_SUPPLIER")
    private supplier supplier;
}
