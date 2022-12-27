package com.example.departmentservice.responseTemplate;

import com.example.departmentservice.dto.costumer.CostumerResponse;
import com.example.departmentservice.dto.costumer.DepartmentResponseCostumer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateCostumer {
    private DepartmentResponseCostumer departmentResponseCostumer;
    private CostumerResponse costumerResponse;
}
