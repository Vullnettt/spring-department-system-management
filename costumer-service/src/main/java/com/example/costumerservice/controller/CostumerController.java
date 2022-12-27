package com.example.costumerservice.controller;

import com.example.costumerservice.dto.CostumerRequest;
import com.example.costumerservice.dto.CostumerResponse;
import com.example.costumerservice.models.Costumer;
import com.example.costumerservice.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/costumer")
public class CostumerController {

    @Autowired
    private CostumerService costumerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCostumer(@RequestBody CostumerRequest costumerRequest){
        costumerService.saveCostumer(costumerRequest);
    }

    @PutMapping("/setByIdCostumerTimeOfExit/{costumerDataId}")
    public void setCostumerTimeOfExits(@PathVariable Long costumerDataId){
        costumerService.setCostumerTimeOfExits(costumerDataId);
    }

    @PutMapping("/setByIdTimeHowLongStay/{costumerDataId}")
    public void setByIdTimeHowLongStay(@PathVariable Long costumerDataId){
        costumerService.setByIdTimeHowLongStay(costumerDataId);
    }

    @GetMapping("/{id}")
    public CostumerResponse getCostumerById(@PathVariable("id") Long costumerId){
        return costumerService.getCostumerById(costumerId);
    }
}
