package org.pilipchuk.diblom.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "boiler")
public class Boiler {
    @Id
    Integer id;
    private Integer boilerTemperature;
    private Integer status;
    private Integer deltaTime;
    private Integer deltaTemp;
    private Integer setTemp;
    private Integer onLine;
    private Integer onLineTemp;
    private Integer currentStatus;
    private String timeCurStatus;
    @ManyToOne
    @JoinColumn(name = "monit_sensor")
    private Sensor sensor;
}
