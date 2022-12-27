package com.example.departmentservice.models;


import com.example.departmentservice.responseTemplate.ResponseTemplateCostumerProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "departments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    private Long employeeId;
    private Long costumerId;
    private String skuCode;

    @OneToOne(cascade = CascadeType.ALL)
    private ResponseTemplateCostumerProduct responseTemplateCostumerProduct;
}
