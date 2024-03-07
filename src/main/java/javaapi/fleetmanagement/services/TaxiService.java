package javaapi.fleetmanagement.services;

import javaapi.fleetmanagement.models.TaxiModel;
import javaapi.fleetmanagement.repositories.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

//ENCAPSULA LA LÓGICA
@Service //indica que esta clase es un servicio de Spring
public class TaxiService {
    @Autowired //inyecta instancia de repository
    TaxiRepository taxiRepository;

    public Page<TaxiModel> getTaxis(Pageable pageable){//define un método para obtener una lista paginada de taxis

        //utiliza el método findAll del repositorio para obtener una página de objetos TaxiModel
        return taxiRepository.findAll(pageable);
    }
}

//Service -> Repository