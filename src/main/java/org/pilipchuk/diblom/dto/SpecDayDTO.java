package org.pilipchuk.diblom.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.pilipchuk.diblom.entities.SpecDaySetup;

import java.util.Date;

@Data
@NoArgsConstructor
public class SpecDayDTO {

    private Integer id;
    private Integer dayFrom;
    private Integer monthFrom;
    private Integer yearFrom;
    private String dateFrom;
    private Integer dayTo;
    private Integer monthTo;
    private Integer yearTo;
    private String dateTo;
    private String timeZoneName;
    private Integer temperature;

    public SpecDayDTO(SpecDaySetup specDaySetup)
    {
        id = specDaySetup.getId();
        dateFrom = String.format("%d.%d.%d", specDaySetup.getDayFrom(), specDaySetup.getMonthFrom(), specDaySetup.getYearFrom());
        dateTo = String.format("%d.%d.%d", specDaySetup.getDayTo(), specDaySetup.getMonthTo(), specDaySetup.getYearTo());
        timeZoneName = specDaySetup.getTimeZone().getName();
        temperature = specDaySetup.getTimeZone().getTemperature();
    }
}
