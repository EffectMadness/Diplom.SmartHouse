package org.pilipchuk.diblom.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import javax.persistence.*;
import java.time.Instant;

import static java.time.Instant.now;

@Data
@NoArgsConstructor
@Entity
@Table(name = "temperature")
public class Temperature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_id")
    private Integer dataId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    private Double temperature;

    @Column(name = "data_time")
    private Instant dataTime;

    public Temperature(Sensor sensor, Double temperature) {
        this.sensor = sensor;
        this.temperature = temperature;
        this.dataTime = now();
    }


}
