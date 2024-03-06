package javaapi.fleetmanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "trajectories")
public class TrajectoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "taxi_id", nullable = false)
    @JsonIgnoreProperties("trajectories")
    private TaxiModel taxi;

    @Column(name = "date")
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