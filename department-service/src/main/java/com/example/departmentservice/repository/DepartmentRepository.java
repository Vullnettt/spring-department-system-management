package com.example.departmentservice.repository;

import com.example.departmentservice.dto.costumer.CostumerResponse;
import com.example.departmentservice.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByEmployeeId(Long employeeId);

    Department findByCostumerId(Long costumerId);

    Department findBySkuCode(String skuCode);

}
