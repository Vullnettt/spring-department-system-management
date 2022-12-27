package com.example.departmentservice.dto.costumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentResponseCostumer {
    private Long id;
    private String name;
    private Long costumerId;
}
