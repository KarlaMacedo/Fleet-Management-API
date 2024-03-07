package javaapi.fleetmanagement.services;

import javaapi.fleetmanagement.models.LastLocationDTO;
import javaapi.fleetmanagement.repositories.LastLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;

//ENCAPSULA LA LÓGICA
@Service//indica que esta clase es un servicio de Spring
public class LastLocationService {
    @Autowired //inyecta instancia de repository
    private LastLocationRepository lastLocationRepository;

    public Page<LastLocationDTO> getLatestTrajectories(Pageable pageable) {//define un método para obtener una lista paginada de ultima ubicación
        //busca ultimas ubicaciones paginadas con método del repository
        Page<Object[]> lastTrajectories = lastLocationRepository.findLastTrajectories(pageable);

        //lastTrajectories.forEach(row -> System.out.println(Arrays.toString(row)));

        // Mapea cada una, las convierte en un objeto LastLocationDTO y las devuelve paginadas
        return lastTrajectories.map(row -> new LastLocationDTO(
                (Integer) row[0], // id
                (String) row[1], // plate
                ((Timestamp) row[2]).toLocalDateTime(), // timestamp
                (Double) row[3], // latitude
                (Double) row[4] // longitude
        ));
    }
}
//Service -> Repository