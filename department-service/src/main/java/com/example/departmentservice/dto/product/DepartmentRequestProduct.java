package com.example.departmentservice.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestProduct {
    private String name;
    private String address;
    private String skuCode;
}
