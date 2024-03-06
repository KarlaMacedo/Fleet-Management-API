package javaapi.fleetmanagement.repositories;

import io.micrometer.common.lang.NonNullApi;
import javaapi.fleetmanagement.models.TaxiModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
@NonNullApi
public interface TaxiRepository extends PagingAndSortingRepository<TaxiModel, Integer> {
    Page<TaxiModel> findAll(Pageable pageable);
}
