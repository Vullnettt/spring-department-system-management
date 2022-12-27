package com.example.departmentservice.controller;

import com.example.departmentservice.dto.*;
import com.example.departmentservice.dto.costumer.DepartmentRequestCostumer;
import com.example.departmentservice.dto.employee.DepartmentRequestEmployee;
import com.example.departmentservice.dto.product.DepartmentRequestProduct;
import com.example.departmentservice.responseTemplate.ResponseTemplateCostumer;
import com.example.departmentservice.responseTemplate.ResponseTemplateCostumerProduct;
import com.example.departmentservice.responseTemplate.ResponseTemplateEmployee;
import com.example.departmentservice.responseTemplate.ResponseTemplateProduct;
import com.example.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDepartment(@RequestBody DepartmentRequestEmployee departmentRequestEmployee){
        departmentService.saveDepartmentWithEmployee(departmentRequestEmployee);
    }
    @PostMapping("/costumer")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDepartmentWithCostumer(@RequestBody DepartmentRequestCostumer departmentRequest){
        departmentService.saveDepartmentWithCostumer(departmentRequest);
    }
    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDepartmentWithProduct(@RequestBody DepartmentRequestProduct departmentRequestProduct){
        departmentService.saveDepartmentWithProduct(departmentRequestProduct);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentResponse> getAllDepartment(){
        return departmentService.getAllDepartment();
    }

    @GetMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseTemplateEmployee findEmployeeWithDepartment(@PathVariable("id") Long employeeId){
        return departmentService.findEmployeeWithDepartment(employeeId);
    }

    @GetMapping("/costumer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseTemplateCostumer findCostumerWithDepartment(@PathVariable("id") Long costumerId){
        return departmentService.findCostumerWithDepartment(costumerId);
    }

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseTemplateProduct findProductWithDepartment(@PathVariable("id") String skuCode){
        return departmentService.findProductWithDepartment(skuCode);
    }

    @GetMapping("/product/isInStock/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean findIfProductIsInStock(@PathVariable("id") String skuCode){
        return departmentService.findProductIfIsInStock(skuCode);
    }

    @PutMapping("/costumer/{costumerId}/product/{skuCode}")
    public ResponseTemplateCostumerProduct addCostumerWithProduct(@PathVariable Long costumerId, @PathVariable String skuCode){
        return departmentService.addCostumerWithProduct(costumerId, skuCode);
    }
}
