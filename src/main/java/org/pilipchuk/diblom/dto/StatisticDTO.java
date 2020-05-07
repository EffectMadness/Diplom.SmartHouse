package org.pilipchuk.diblom.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class StatisticDTO {
    private Integer sensorId;
    private Instant dataTime;
    private String sensorName;
    private Double temperature;

}
