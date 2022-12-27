package com.example.departmentservice.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentResponseProduct {
        private Long id;
        private String name;
        private String address;
        private String skuCode;
}
