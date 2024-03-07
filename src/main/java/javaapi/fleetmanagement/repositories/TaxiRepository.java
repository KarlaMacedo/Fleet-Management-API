package javaapi.fleetmanagement.repositories;

import io.micrometer.common.lang.NonNullApi;
import javaapi.fleetmanagement.models.TaxiModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

//MÉTODOS PARA INTERACTUAR CON LA BD
@Repository // indica que la interfaz es un componente de Spring que manejará la interacción con la BD
@NonNullApi // indica que ningun elemento puede ser nulo
public interface TaxiRepository extends PagingAndSortingRepository<TaxiModel, Integer> { //extiende la interfaz para tener métodos para realizar operaciones CRUD y paginación
    Page<TaxiModel> findAll(Pageable pageable); //página de objetos TaxiModel
}

//Repository -> Finally Controller Response
