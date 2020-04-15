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
    private Integer dec0;
    private Integer dec1;
    private Integer dec2;
    private Integer dec3;
    private Integer dec4;
    private Integer dec5;
    private Integer dec6;
    private Integer dec7;
}
