package org.pilipchuk.diblom.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.pilipchuk.diblom.dto.SpecDayDTO;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "spec_day_setup")
public class SpecDaySetup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer dayFrom;
    Integer monthFrom;
    Integer yearFrom;
    Integer dayTo;
    Integer monthTo;
    Integer yearTo;
    //String  dateFrom;
    //String  dateTo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "time_zone")
    TimeZone timeZone;

    public SpecDaySetup absorb(SpecDayDTO dto, TimeZone timeZone) {
        String[] splitedDate = dto.getDateFrom().split("\\.");
        dayFrom = Integer.valueOf(splitedDate[0]);
        monthFrom = Integer.valueOf(splitedDate[1]);
        yearFrom = Integer.valueOf(splitedDate[2]);
        splitedDate = dto.getDateTo().split("\\.");
        dayTo = Integer.valueOf(splitedDate[0]);
        monthTo = Integer.valueOf(splitedDate[1]);
        yearTo = Integer.valueOf(splitedDate[2]);
        this.timeZone = timeZone;
        return this;
    }
}