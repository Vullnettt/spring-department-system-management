package com.example.departmentservice.service;

import com.example.departmentservice.dto.*;
import com.example.departmentservice.dto.costumer.CostumerResponse;
import com.example.departmentservice.dto.costumer.DepartmentRequestCostumer;
import com.example.departmentservice.dto.costumer.DepartmentResponseCostumer;
import com.example.departmentservice.dto.employee.DepartmentRequestEmployee;
import com.example.departmentservice.dto.employee.DepartmentResponseEmployee;
import com.example.departmentservice.dto.employee.EmployeeResponse;
import com.example.departmentservice.dto.product.DepartmentRequestProduct;
import com.example.departmentservice.dto.product.DepartmentResponseProduct;
import com.example.departmentservice.dto.product.ProductResponse;
import com.example.departmentservice.models.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.responseTemplate.ResponseTemplateCostumer;
import com.example.departmentservice.responseTemplate.ResponseTemplateCostumerProduct;
import com.example.departmentservice.responseTemplate.ResponseTemplateEmployee;
import com.example.departmentservice.responseTemplate.ResponseTemplateProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private RestTemplate restTemplate;

    public void saveDepartmentWithEmployee(DepartmentRequestEmployee departmentRequestEmployee){
        Department department = Department.builder()
                .name(departmentRequestEmployee.getName())
                .address(departmentRequestEmployee.getAddress())
                .employeeId(departmentRequestEmployee.getEmployeeId())
                .build();
        log.info("Save department with employee...");
        departmentRepository.save(department);
    }

    public void saveDepartmentWithCostumer(DepartmentRequestCostumer departmentRequest){
        Department department = Department.builder()
                .name(departmentRequest.getName())
                .address(departmentRequest.getAddress())
                .costumerId(departmentRequest.getCostumerId())
                .build();
        log.info("Save department with costumer...");
        departmentRepository.save(department);
    }

    public void saveDepartmentWithProduct(DepartmentRequestProduct departmentRequestProduct){
        Department department = Department.builder()
                .name(departmentRequestProduct.getName())
                .address(departmentRequestProduct.getAddress())
                .skuCode(departmentRequestProduct.getSkuCode())
                .build();
        log.info("Save department with product...");
        departmentRepository.save(department);
    }

    public List<DepartmentResponse> getAllDepartment(){
        List<Department> department = departmentRepository.findAll();
        log.info("Get all departments...");
        return department.stream().map(this::mapToDepartmentResponse).toList();
    }

    private DepartmentResponse mapToDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .address(department.getAddress())
                .employeeId(department.getEmployeeId())
                .costumerId(department.getCostumerId())
                .skuCode(department.getSkuCode())
                .build();
    }

    public ResponseTemplateEmployee findEmployeeWithDepartment(Long employeeId){
        ResponseTemplateEmployee responseTemplateEmployee = new ResponseTemplateEmployee();
        Department department = departmentRepository.findByEmployeeId(employeeId);
        DepartmentResponseEmployee departmentResponseEmployee = DepartmentResponseEmployee.builder()
                .id(department.getId())
                .name(department.getName())
                .employeeId(department.getEmployeeId())
                .build();
        EmployeeResponse employeeResponse = restTemplate.getForObject("http://employee-service/api/employee/" +
                departmentResponseEmployee.getEmployeeId(),
                EmployeeResponse.class);

        responseTemplateEmployee.setDepartmentResponseEmployee(departmentResponseEmployee);
        responseTemplateEmployee.setEmployeeResponse(employeeResponse);
        log.info("Get employee with department");
        return responseTemplateEmployee;
    }

    public ResponseTemplateCostumer findCostumerWithDepartment(Long costumerId){
        ResponseTemplateCostumer responseTemplateCostumer = new ResponseTemplateCostumer();
        Department department = departmentRepository.findByCostumerId(costumerId);
        DepartmentResponseCostumer departmentResponseCostumer = DepartmentResponseCostumer.builder()
                .id(department.getId())
                .name(department.getName())
                .costumerId(department.getCostumerId())
                .build();
        CostumerResponse costumerResponse = restTemplate.getForObject("http://costumer-service/api/costumer/"+
                departmentResponseCostumer.getCostumerId(),
                CostumerResponse.class);

        responseTemplateCostumer.setDepartmentResponseCostumer(departmentResponseCostumer);
        responseTemplateCostumer.setCostumerResponse(costumerResponse);
        log.info("Get costumer with department");
        return responseTemplateCostumer;
    }

    public ResponseTemplateProduct findProductWithDepartment(String skuCode){
        ResponseTemplateProduct responseTemplateProduct = new ResponseTemplateProduct();
        Department department = departmentRepository.findBySkuCode(skuCode);
        DepartmentResponseProduct departmentResponseProduct = DepartmentResponseProduct.builder()
                .id(department.getId())
                .name(department.getName())
                .skuCode(department.getSkuCode())
                .build();
        ProductResponse productResponse = restTemplate.getForObject("http://product-service/api/product/" +
                departmentResponseProduct.getSkuCode(),
                ProductResponse.class
                );

        responseTemplateProduct.setDepartmentResponseProduct(departmentResponseProduct);
        responseTemplateProduct.setProductResponse(productResponse);
        log.info("Get product with department");
        return responseTemplateProduct;
    }

    public boolean findProductIfIsInStock(String skuCode){
        ResponseTemplateProduct responseTemplateProduct = new ResponseTemplateProduct();
        Department department = departmentRepository.findBySkuCode(skuCode);
        DepartmentResponseProduct departmentResponseProduct = DepartmentResponseProduct.builder()
                .id(department.getId())
                .name(department.getName())
                .address(department.getAddress())
                .skuCode(department.getSkuCode())
                .build();
        ProductResponse productResponse = restTemplate.getForObject("http://product-service/api/product/" +
                        departmentResponseProduct.getSkuCode(),
                ProductResponse.class
        );

        responseTemplateProduct.setDepartmentResponseProduct(departmentResponseProduct);
        responseTemplateProduct.setProductResponse(productResponse);
        log.info("Get product if is in stock");
        if(productResponse.getQuantity() > 0){
            return true;
        }else {
            return false;
        }
    }

    public ResponseTemplateCostumerProduct addCostumerWithProduct(Long costumerId, String skuCode){
            Department department = departmentRepository.findBySkuCode(skuCode);
            Department department1 = departmentRepository.findByCostumerId(costumerId);

            CostumerResponse costumerResponse = restTemplate.getForObject("http://costumer-service/api/costumer/" +
                            department1.getCostumerId(),
                    CostumerResponse.class
            );
            ProductResponse productResponse = restTemplate.getForObject("http://product-service/api/product/" +
                            department.getSkuCode(),
                    ProductResponse.class
            );
        if(findProductIfIsInStock(skuCode)) {
            for(int i = 1; i >= 1; i --) {
                productResponse.setQuantity(productResponse.getQuantity() - i);
            }
            costumerResponse.setSkuCode(productResponse.getSkuCode());
            costumerResponse.setCostumerAfterBuyProduct(costumerResponse.getMoney() - productResponse.getPrice());

                ResponseTemplateCostumerProduct responseTemplateCostumerProductRepository = new ResponseTemplateCostumerProduct().builder()
                        .costumerId(costumerResponse.getCostumerId())
                        .firstName(costumerResponse.getFirstName())
                        .money(costumerResponse.getMoney())
                        .productSkuCode(costumerResponse.getSkuCode())
                        .skuCode(costumerResponse.getSkuCode())
                        .name(productResponse.getName())
                        .price(productResponse.getPrice())
                        .costumerMoneyAfterBuyProduct(costumerResponse.getCostumerAfterBuyProduct())
                        .quantity(productResponse.getQuantity())
                        .build();
            log.info("Add costumer with product");
                return saveCostumerProduct(responseTemplateCostumerProductRepository);
            }
                else {
                throw new RuntimeException("Product is not in stock, try again later");
            }
    }

    public ResponseTemplateCostumerProduct saveCostumerProduct(ResponseTemplateCostumerProduct responseTemplateCostumerProduct){
        Department department = Department.builder()
                .responseTemplateCostumerProduct(responseTemplateCostumerProduct)
                .build();
        departmentRepository.save(department);
        log.info("Save costumer with product");
        return responseTemplateCostumerProduct;
    }

}
