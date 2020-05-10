package org.pilipchuk.diblom.controller;

import org.modelmapper.ModelMapper;
import org.pilipchuk.diblom.dao.StatisticDao;

import org.pilipchuk.diblom.dto.StatisticDTO;
import org.pilipchuk.diblom.dto.jtable.jTableEntityList;
import org.pilipchuk.diblom.entities.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private StatisticDao statisticDao;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/max")
    public jTableEntityList<StatisticDTO> max(@RequestParam(required = false) Integer hours) {
        if(hours == null) {
            hours = 150;
        }
        hours = 150;
        Instant checkDateTime = Instant.now().plus(-1 * hours, ChronoUnit.HOURS);
        Iterable<Temperature> target = statisticDao.getMax(checkDateTime);
        List<StatisticDTO> dtoList = new ArrayList<>();
        map(target, dtoList);

        return new jTableEntityList<>("OK", dtoList, dtoList.size());
    }

    @PostMapping("/min")
    public jTableEntityList<StatisticDTO> min(@RequestParam(required = false) Integer hours) {
        if(hours == null) {
            hours = 24;
        }
        hours = 150;
        Instant checkDateTime = Instant.now().plus(-1 * hours, ChronoUnit.HOURS);
        Iterable<Temperature> target = statisticDao.getMin(checkDateTime);
        List<StatisticDTO> dtoList = new ArrayList<>();
        map(target, dtoList);

        return new jTableEntityList<>("OK", dtoList, dtoList.size());
    }

    private void map(Iterable<Temperature> temperatures, List<StatisticDTO> statisticDTOs){
        temperatures.forEach(temperature -> {
            statisticDTOs.add(new StatisticDTO(temperature.getSensor().getSensorId(),
                                               temperature.getDataTime(),
                                               temperature.getSensor().getSensorName(),
                                               temperature.getTemperature()));
        });
    }

}
