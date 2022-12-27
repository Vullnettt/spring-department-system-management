package com.example.costumerservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "costumer_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostumerData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long costumerDataId;
    private LocalTime timeOfEnter;
    private LocalTime timeOfExit;
    private LocalTime timeHowLongStay;

    public CostumerData(LocalTime timeOfEnter) {
        this.timeOfEnter = timeOfEnter;
    }
}
