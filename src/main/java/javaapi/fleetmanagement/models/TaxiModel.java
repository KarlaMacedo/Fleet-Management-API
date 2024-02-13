package javaapi.fleetmanagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "taxis")
public class TaxiModel {
    @Id
    @Column(unique = true, nullable = false)
    private Integer id;
    private String plate;


    public Integer getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

}