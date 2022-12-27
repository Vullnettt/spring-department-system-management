package com.example.departmentservice.dto.costumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestCostumer {
    private String name;
    private String address;
    private Long costumerId;
}
