package com.example.departmentservice.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestEmployee {
    private String name;
    private String address;
    private Long employeeId;
}
