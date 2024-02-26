package javaapi.fleetmanagement.repositories;

import javaapi.fleetmanagement.models.LastLocationDTO;
import javaapi.fleetmanagement.models.TrajectoryModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

@Repository
public interface LastLocationRepository extends JpaRepository<LastLocationDTO, Integer> {
    @Query(value = "SELECT DISTINCT ON (taxis.id) taxis.id, taxis.plate, trajectories.date, trajectories.latitude, trajectories.longitude " +
            "FROM taxis " +
            "JOIN trajectories ON taxis.id = trajectories.taxi_id " +
            "ORDER BY taxis.id, trajectories.date DESC", nativeQuery = true)
            Page<Object[]> findLastTrajectories(Pageable pageable);
}
