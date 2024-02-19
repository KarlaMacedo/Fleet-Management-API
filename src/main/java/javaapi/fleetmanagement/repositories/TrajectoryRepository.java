package javaapi.fleetmanagement.repositories;

import javaapi.fleetmanagement.models.TrajectoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TrajectoryRepository extends PagingAndSortingRepository<TrajectoryModel, Integer> {

    Page<TrajectoryModel> findByTaxiIdAndDate(int taxiId, LocalDate date, Pageable pageable);
}
