package javaapi.fleetmanagement.repositories;

import javaapi.fleetmanagement.models.LastLocationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

//MÉTODOS PARA INTERACTUAR CON LA BD
@Repository// indica que la interfaz es un componente de Spring que manejará la interacción con la BD
public interface LastLocationRepository extends JpaRepository<LastLocationDTO, Integer> {

    //define consulta nativa de SQL personalizada para buscar las últimas trayectorias de los taxis
    @Query(value = "SELECT DISTINCT ON (taxis.id) taxis.id, taxis.plate, trajectories.date, trajectories.latitude, trajectories.longitude " +
            "FROM taxis " +
            "JOIN trajectories ON taxis.id = trajectories.taxi_id " +
            "ORDER BY taxis.id, trajectories.date DESC", //la primera fila para cada taxiID será la última trayectoria registrada para ese taxi
            nativeQuery = true)
            Page<Object[]> findLastTrajectories(Pageable pageable);//Devuelve la paginación del array de objetos de los resultados
}
//Repository -> Finally Controller Response
