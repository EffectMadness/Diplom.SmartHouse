package org.pilipchuk.diblom.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.pilipchuk.diblom.entities.DaySetup;
import org.pilipchuk.diblom.entities.TimeZone;

@Data
@NoArgsConstructor
public class DaySetupDTO {
    private Integer id;
    private String day;
    private String period;
    private String timeZoneName;
    private Integer temperature;

    public DaySetupDTO(DaySetup daySetup) {
        id = daySetup.getId();
        day = daySetup.getDay();
        TimeZone timeZone = daySetup.getTimeZone();
        timeZoneName = timeZone.getName();
        period = String.format("%d:%d - %d:%d",
                timeZone.getStartHour(), timeZone.getStartMin(),
                timeZone.getEndHour(), timeZone.getEndMin());
        temperature = timeZone.getTemperature();
    }
}
