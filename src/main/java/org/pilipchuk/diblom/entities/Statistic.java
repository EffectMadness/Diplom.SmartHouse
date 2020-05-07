package org.pilipchuk.diblom.entities;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Data
@Entity
public class Statistic {

    @Id
    private Integer sensorId;
    private Instant dataTime;
    private String sensorName;
    private Double temperature;

}
