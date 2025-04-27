package com.StoreManagement.mappers;

import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class supplierDTO {
    private Long supplierId;
    private String supplierName;
    private String supplierAddress;
    private List<productInfo> supplierProducts;
}
