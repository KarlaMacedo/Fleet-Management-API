package javaapi.fleetmanagement.models;

import jakarta.persistence.*;
import org.hibernate.type.descriptor.jdbc.TimestampWithTimeZoneJdbcType;

import java.time.OffsetDateTime;

@Entity
@Table(name = "trajectories")
public class TrajectoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name= "taxi_id", referencedColumnName = "id")
    private TaxiModel taxi;

    private OffsetDateTime date;

    private Double latitude;

    private Double longitude;

    public TrajectoryModel() {
    }

    public TrajectoryModel(Integer id, TaxiModel taxi, OffsetDateTime date, Double latitude, Double longitude) {
        this.id = id;
        this.taxi = taxi;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TaxiModel getTaxi() {
        return taxi;
    }

    public void setTaxi(TaxiModel taxi) {
        this.taxi = taxi;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}