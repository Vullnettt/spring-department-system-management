package com.example.departmentservice.responseTemplate;

import com.example.departmentservice.dto.employee.DepartmentResponseEmployee;
import com.example.departmentservice.dto.employee.EmployeeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateEmployee {

    private DepartmentResponseEmployee departmentResponseEmployee;
    private EmployeeResponse employeeResponse;
}
