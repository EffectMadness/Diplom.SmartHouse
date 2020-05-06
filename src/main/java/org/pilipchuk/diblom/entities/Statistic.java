package org.pilipchuk.diblom.entities;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@NoArgsConstructor
public class Statistic {

    @Id
    private Integer sensorId;
    private Instant dataTime;
    private String sensorName;
    private Double temperature;

}
