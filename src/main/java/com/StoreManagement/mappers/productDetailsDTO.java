package com.StoreManagement.mappers;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class productDetailsDTO {
    private Long productId;
    private String productName;
    private Double productPrice;
    private String supplierName;
    private Long supplierId;
}
