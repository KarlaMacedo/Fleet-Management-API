package javaapi.fleetmanagement.repositories;

import javaapi.fleetmanagement.models.TrajectoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

//MÉTODOS PARA INTERACTUAR CON LA BD
@Repository// indica que la interfaz es un componente de Spring que manejará la interacción con la BD
public interface TrajectoryRepository extends PagingAndSortingRepository<TrajectoryModel, Integer> {

    //define consulta nativa de SQL personalizada para buscar trayectorias por taxiId y periodo de tiempo Y las cuenta
    @Query(value = "SELECT * FROM trajectories WHERE taxi_id = ?1 AND date BETWEEN ?2 AND ?3",
            countQuery = "SELECT count(*) FROM trajectories WHERE taxi_id = ?1 AND date BETWEEN ?2 AND ?3",
            nativeQuery = true)
    Page<TrajectoryModel> findByTaxiIdAndDate(int taxiId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
//Repository -> Finally Controller Response