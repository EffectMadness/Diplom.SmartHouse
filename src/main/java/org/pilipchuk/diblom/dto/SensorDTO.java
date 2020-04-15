package org.pilipchuk.diblom.dto;

import lombok.Data;

@Data
public class SensorDTO {
    private Integer sensorId;
    private String sensorName;
    private Integer delta;
    private String sensorUid;
}
