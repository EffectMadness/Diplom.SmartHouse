package org.pilipchuk.diblom.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
//import org.pilipchuk.diblom.dto.SpecDayDTO;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "temperature")

public class Temperature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer data_id;
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
    private Float temperature;
    private DateTime data_time;

}
