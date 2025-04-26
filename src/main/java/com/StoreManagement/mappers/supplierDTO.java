package com.StoreManagement.mappers;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class supplierDTO {
    private Long supplierId;
    private String supplierName;
    private String supplierAddress;
}
