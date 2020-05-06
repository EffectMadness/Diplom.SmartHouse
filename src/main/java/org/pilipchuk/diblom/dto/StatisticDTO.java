package org.pilipchuk.diblom.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.pilipchuk.diblom.entities.Statistic;

import java.time.Instant;

@Data
@NoArgsConstructor
public class StatisticDTO {
    private Instant dataTime;
    private String sensorName;
    private Double temperature;

}
