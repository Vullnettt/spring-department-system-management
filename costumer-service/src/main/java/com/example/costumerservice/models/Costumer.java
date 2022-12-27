package com.example.costumerservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "costumers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long costumerId;
    private String firstName;
    private String lastName;
    private String email;
    //Remove skuCode
//    private String skuCode;
    private Double money;

    @OneToOne(cascade = CascadeType.ALL)
    private CostumerData costumerData;

    public void setCostumerData(CostumerData costumerData) {
        this.costumerData = costumerData;
    }
}
