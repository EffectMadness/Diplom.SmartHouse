package org.pilipchuk.diblom.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {

    @Id
    private Integer sensorId;
    private Instant dataTime;
    private String sensorName;
    private Double temperature;

}
