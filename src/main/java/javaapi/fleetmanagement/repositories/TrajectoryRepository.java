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

@Repository
public interface TrajectoryRepository extends PagingAndSortingRepository<TrajectoryModel, Integer> {

    @Query(value = "SELECT * FROM trajectories WHERE taxi_id = ?1 AND date BETWEEN ?2 AND ?3",
            countQuery = "SELECT count(*) FROM trajectories WHERE taxi_id = ?1 AND date BETWEEN ?2 AND ?3",
            nativeQuery = true)
    Page<TrajectoryModel> findByTaxiIdAndDate(int taxiId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}