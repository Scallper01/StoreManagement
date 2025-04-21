package com.StoreManagement.dataAccess;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="SUPPLIERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class supplier {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
}
