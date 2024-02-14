package javaapi.fleetmanagement.services;

import javaapi.fleetmanagement.models.TaxiModel;
import javaapi.fleetmanagement.repositories.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

@Service
public class TaxiService {
    @Autowired
    TaxiRepository taxiRepository;

    public Page<TaxiModel> getTaxis(Pageable pageable){
        return taxiRepository.findAll(pageable);
    }

//    public TaxiModel saveTaxi(TaxiModel taxi){
//        return taxiRepository.save(taxi);
//    }
}