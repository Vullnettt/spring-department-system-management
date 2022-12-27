package com.example.costumerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostumerResponse {
    private Long costumerId;
    private String firstName;
    private String lastName;
    private String email;
    private String skuCode;
    private Double money;
}
