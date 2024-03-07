package javaapi.fleetmanagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//REPRESENTA UNA TABLA EN LA BD
@Entity // indica que la clase es una entidad JPA (Java Persistence API) que se mapeará una tabla en de la BD
@Table(name = "taxis") // nombre de la tabla de la BD
public class TaxiModel {
    @Id //marca el id como primary key
    @Column(unique = true, nullable = false) //restricciones en la columna
    private Integer id;
    private String plate;

    public TaxiModel() {
    }

    public TaxiModel(Integer id, String plate) {
        this.id = id;
        this.plate = plate;
    }

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