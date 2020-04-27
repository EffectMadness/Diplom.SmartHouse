package org.pilipchuk.diblom.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sensorId;

    private String sensorName;
    private Integer delta;
    private String type;
    private String sensorUid;
    private Double temperatureValue;

    public Double getTemperatureValue() {
        return temperatureValue == null ? 0 : temperatureValue;
    }
}
