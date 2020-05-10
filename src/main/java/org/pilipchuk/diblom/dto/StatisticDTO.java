package org.pilipchuk.diblom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {
    private Integer sensorId;
    private Instant dataTime;
    private String sensorName;
    private Double temperature;

}
