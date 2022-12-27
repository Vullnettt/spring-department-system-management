package com.example.costumerservice.repository;

import com.example.costumerservice.models.CostumerData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerDataRepository extends JpaRepository<CostumerData, Long> {
}
