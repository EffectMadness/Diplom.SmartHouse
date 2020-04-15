package org.pilipchuk.diblom.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "time_zone")
public class TimeZone {
    @Id
    private String name;
    private Integer startHour;
    private Integer startMin;
    private Integer endHour;
    private Integer endMin;
    private Integer temperature;
    private Integer active;

}
