package com.StoreManagement.entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="ORDERS_CONTENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderContent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ID_ORDER")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "ID_PRODUCT")
    private Product product;
    private Integer quantity;
}
