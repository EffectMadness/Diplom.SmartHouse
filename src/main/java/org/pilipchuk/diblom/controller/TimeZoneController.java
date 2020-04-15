package org.pilipchuk.diblom.controller;

import org.pilipchuk.diblom.dao.TimeZoneDao;
import org.pilipchuk.diblom.dto.jtable.JTableOption;
import org.pilipchuk.diblom.dto.jtable.JTableOptionsList;
import org.pilipchuk.diblom.entities.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/time-zone")
public class TimeZoneController {

    @Autowired
    private TimeZoneDao timeZoneDao;

    @PostMapping
    public JTableOptionsList getAsOptions() {
        List<JTableOption> options = StreamSupport.stream(timeZoneDao.findAll().spliterator(), false)
                .map(timeZone -> new JTableOption(
                        String.format("%s (%d:%d - %d:%d)", timeZone.getName(), timeZone.getStartHour(), timeZone.getStartMin(),
                                timeZone.getEndHour(), timeZone.getEndMin()),
                        timeZone.getName()))
                .collect(Collectors.toList());
        return new JTableOptionsList("OK", options);

    }
}
