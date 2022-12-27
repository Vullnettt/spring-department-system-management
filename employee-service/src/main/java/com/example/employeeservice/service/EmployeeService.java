package com.example.employeeservice.service;

import com.example.employeeservice.dto.EmployeeRequest;
import com.example.employeeservice.dto.EmployeeResponse;
import com.example.employeeservice.models.Employee;
import com.example.employeeservice.models.EmployeeData;
import com.example.employeeservice.repository.EmployeeDataRepository;
import com.example.employeeservice.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeDataRepository employeeDataRepository;

    public void saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder()
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .email(employeeRequest.getEmail())
                .dateOfBirth(employeeRequest.getDateOfBirth())
                .build();


        Integer localDate = LocalDate.now().getYear();
        Integer birthDay = employee.getDateOfBirth().getYear();
        Integer age = localDate -= birthDay;

        EmployeeData employeeData = new EmployeeData(
                LocalDate.now(),
                age
        );
        if (employeeData.getAge() >= 18) {

            for (long empId = 1; empId <= 1; empId++) {
                employeeRepository.findById(empId);
                employeeDataRepository.findById(empId);
            }
            employee.setEmployeeData(employeeData);
            employeeRepository.save(employee);
            log.info("Employee employeeId {}, {}, employeeDataId {} is saved", employee.getEmployeeId(), employee.getEmail(), employeeData.getId());
        }else{
            throw new IllegalArgumentException("You're under 18 years old");
        }
    }
    public List<EmployeeResponse> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        log.info("Get all employees");
        return employees.stream().map(this::mapToEmployeeResponse).toList();
    }

    private EmployeeResponse mapToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .dateOfBirth(employee.getDateOfBirth())
                .build();
    }

    public EmployeeResponse getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        log.info("Get employee by id: {}", employee.getEmployeeId());
            return EmployeeResponse.builder()
                    .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .dateOfBirth(employee.getDateOfBirth())
                .build();
    }
}
