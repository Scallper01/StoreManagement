package com.StoreManagement.mappers;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class warehouseDTO {

    private String warehouseName;
    private List<inventoryDTO> warehouseInventory;

}
