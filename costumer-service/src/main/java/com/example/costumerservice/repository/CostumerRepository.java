package com.example.costumerservice.repository;

import com.example.costumerservice.models.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerRepository extends JpaRepository<Costumer, Long> {
    Costumer findByCostumerId(Long costumerId);
}
