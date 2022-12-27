package com.example.departmentservice.responseTemplate;

import com.example.departmentservice.dto.product.DepartmentResponseProduct;
import com.example.departmentservice.dto.product.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateProduct {
    private DepartmentResponseProduct departmentResponseProduct;
    private ProductResponse productResponse;
}
