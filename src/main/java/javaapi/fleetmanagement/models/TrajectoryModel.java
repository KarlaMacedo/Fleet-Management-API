package javaapi.fleetmanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

//REPRESENTA UNA TABLA EN LA BD
@Entity// indica que la clase es una entidad JPA (Java Persistence API) que se mapeará una tabla en de la BD
@Table(name = "trajectories")// nombre de la tabla de la BD
public class TrajectoryModel {

    @Id//marca el id como primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que el id se generará automáticamente por la estrategia de BD identidad
    private int id;

    @ManyToOne //indica relación de muchos a uno con TaxiModel
    @JoinColumn(name = "taxi_id", nullable = false) // columna externa a unir
    @JsonIgnoreProperties("trajectories") // ignorar propiedad trajectories al serializar/deserializar objetos TaxiModel
    private TaxiModel taxi;

    @Column(name = "date")//config de la columna
    private LocalDateTime date;

    private double latitude;

    private double longitude;

    public TrajectoryModel() {
    }

    public TrajectoryModel(int id, TaxiModel taxi, LocalDateTime dateTime, double latitude, double longitude) {
        this.id = id;
        this.taxi = taxi;
        this.date = dateTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaxiModel getTaxi() {
        return taxi;
    }

    public void setTaxi(TaxiModel taxi) {
        this.taxi = taxi;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}