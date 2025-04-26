package com.StoreManagement.mappers;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class customerDTO {
    private Long customerId;
    private String customerName;
    private String CustomerAddress;
}
