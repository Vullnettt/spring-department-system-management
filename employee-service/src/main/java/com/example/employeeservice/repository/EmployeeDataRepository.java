package com.example.employeeservice.repository;

import com.example.employeeservice.models.EmployeeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeDataRepository extends JpaRepository<EmployeeData, Long> {

    Optional<EmployeeData> findById(Long id);

    @Query("Select e From EmployeeData e Where e.age > 17")
    boolean employeeGreater();
}
