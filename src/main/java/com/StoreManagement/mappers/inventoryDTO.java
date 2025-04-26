package com.StoreManagement.mappers;


import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class inventoryDTO {
    private String productName;
    private Long productId;
    private Integer productQuantity;
    private String supplierName;
}
