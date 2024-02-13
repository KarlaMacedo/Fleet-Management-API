package javaapi.fleetmanagement.services;

import javaapi.fleetmanagement.models.TaxiModel;
import javaapi.fleetmanagement.repositories.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TaxiService {
    @Autowired
    TaxiRepository taxiRepository;

    public ArrayList<TaxiModel> getTaxis(){
        return (ArrayList<TaxiModel>) taxiRepository.findAll();
    }

//    public TaxiModel saveTaxi(TaxiModel taxi){
//        return taxiRepository.save(taxi);
//    }
}
