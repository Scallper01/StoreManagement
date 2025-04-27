package com.StoreManagement.mappers;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class productInfo {
    private Long productId;
    private String productName;
}
