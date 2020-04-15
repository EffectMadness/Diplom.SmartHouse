package org.pilipchuk.diblom.dto;

import org.joda.time.DateTime;
import org.pilipchuk.diblom.entities.Sensor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class TemperatureDTO {

    private Float temperature;
    private DateTime data_time;
}
