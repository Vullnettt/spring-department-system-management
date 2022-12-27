package com.example.departmentservice.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentResponseEmployee {
    private Long id;
    private String name;
    private Long employeeId;
}
