package com.example.costumerservice.service;

import com.example.costumerservice.dto.CostumerRequest;
import com.example.costumerservice.dto.CostumerResponse;
import com.example.costumerservice.models.Costumer;
import com.example.costumerservice.models.CostumerData;
import com.example.costumerservice.repository.CostumerDataRepository;
import com.example.costumerservice.repository.CostumerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class CostumerService {

    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private CostumerDataRepository costumerDataRepository;

    public void saveCostumer(CostumerRequest costumerRequest) {
        Costumer costumer = Costumer.builder()
                .firstName(costumerRequest.getFirstName())
                .lastName(costumerRequest.getLastName())
                .email(costumerRequest.getEmail())
                .money(costumerRequest.getMoney())
                .build();

                CostumerData costumerData = new CostumerData(
                LocalTime.now()
                );

        for(long costumersId = 1; costumersId <= 1; costumersId++){
            costumerRepository.findById(costumersId);
            costumerDataRepository.findById(costumersId);
        }
        costumer.setCostumerData(costumerData);
        costumerRepository.save(costumer);
        log.info("Costumer and costumer data is saved, costumerId: {}, costumerFirstName: {}, costumerDataId: {}",
                costumer.getCostumerId(), costumer.getFirstName(), costumerData.getCostumerDataId());
    }

    public CostumerData setCostumerTimeOfExits( Long costumerDataId) {
        try {
            CostumerData costumerData = costumerDataRepository.findById(costumerDataId).get();
            costumerData.setTimeOfExit(LocalTime.now());
            log.info("Set costumer time when he exit");
            return costumerDataRepository.save(costumerData);
        }catch (NoSuchElementException exception){
            throw new NoSuchElementException(exception);
        }
    }
    public CostumerData setByIdTimeHowLongStay(Long costumerDataId) {
        try {
            CostumerData costumerData = costumerDataRepository.findById(costumerDataId).get();

            Integer costumerDataExitHour = costumerData.getTimeOfExit().getHour();
            Integer costumerDataEnterHour = costumerData.getTimeOfEnter().getHour();
            Integer sumOfHours = costumerDataExitHour - costumerDataEnterHour;

            Integer costumerDataExitMinute = costumerData.getTimeOfExit().getMinute();
            Integer costumerDataEnterMinutes = costumerData.getTimeOfEnter().getMinute();
            Integer sumOfMinutes = costumerDataExitMinute - costumerDataEnterMinutes;

            Integer costumerDataExitSecond = costumerData.getTimeOfExit().getSecond();
            Integer costumerDataEnterSecond = costumerData.getTimeOfEnter().getSecond();
            Integer sumOfSecond = costumerDataExitSecond - costumerDataEnterSecond;

            costumerData.setTimeHowLongStay(LocalTime.of(sumOfHours, sumOfMinutes, sumOfSecond));

            log.info("Set costumer time how long stay in department");
            return costumerDataRepository.save(costumerData);
        }catch (NoSuchElementException exception){
            throw new NoSuchElementException(exception);
        }
    }

    public CostumerResponse getCostumerById(Long costumerId){
        Costumer costumer = costumerRepository.findByCostumerId(costumerId);
        log.info("Get costumer by id");
        return CostumerResponse.builder()
                .costumerId(costumer.getCostumerId())
                .firstName(costumer.getFirstName())
                .lastName(costumer.getLastName())
                .email(costumer.getEmail())
                .money(costumer.getMoney())
                .build();
    }
}
