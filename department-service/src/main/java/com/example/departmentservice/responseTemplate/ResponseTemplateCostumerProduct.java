package com.example.departmentservice.responseTemplate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "response_costumer_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseTemplateCostumerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long responseId;

    private Long costumerId;
    private String firstName;
    private Double money;
    private String productSkuCode;

    private String skuCode;
    private String name;
    private Double price;
    private Integer quantity;

    private Double costumerMoneyAfterBuyProduct;
}
