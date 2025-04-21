package com.StoreManagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="SUPPLIERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
}
