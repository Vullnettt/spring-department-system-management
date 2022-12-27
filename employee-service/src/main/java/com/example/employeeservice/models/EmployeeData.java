package com.example.employeeservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dayOfApply;
    private Integer age;

    public EmployeeData(LocalDate dayOfApply, Integer age) {
        this.dayOfApply = dayOfApply;
        this.age = age;
    }
}
