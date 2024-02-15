package javaapi.fleetmanagement.repositories;

import javaapi.fleetmanagement.models.TrajectoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrajectoryRepository extends JpaRepository<TrajectoryModel, Integer> {
    Page<TrajectoryModel> findByTaxiIdAndDate(Integer taxiId, String date, Pageable pageable);
}
