package com.StoreManagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="ORDERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @OneToMany(mappedBy = "order")
    //cascade true : ensures that associated ordercontent are persisted too
    // orphanremoval ensure that deleting and order it deletes the enfants from ordercontent
    private Collection<OrderContent> contents;
    @ManyToOne
    @JoinColumn(name = "ID_CUSTOMER")
    private Customer customer;
}
