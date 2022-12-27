package com.example.costumerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostumerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Double money;
}
