package com.example.employeeservice.repository;

import com.example.employeeservice.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    Optional<Employee> findById(Long id);

    Employee findByEmployeeId(Long employeeId);
}
