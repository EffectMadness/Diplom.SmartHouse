package org.pilipchuk.diblom.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.pilipchuk.diblom.dao.StatisticDao;

import org.pilipchuk.diblom.dto.DaySetupDTO;
import org.pilipchuk.diblom.dto.StatisticDTO;
import org.pilipchuk.diblom.dto.jtable.JTableRequest;
import org.pilipchuk.diblom.dto.jtable.jTableEntityList;
import org.pilipchuk.diblom.entities.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private StatisticDao statisticDao;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/min{hours}")
    public List<Statistic> getStatisticMin(@PathVariable Integer hours) {
        Instant checkDateTime = Instant.now().plus(-1 * hours, ChronoUnit.HOURS);
        return statisticDao.getMin(checkDateTime);
    }

    @GetMapping("/max{hours}")
    public List<Statistic> getStatisticMax(@PathVariable Integer hours) {
        Instant checkDateTime = Instant.now().plus(-1 * hours, ChronoUnit.HOURS);
        return statisticDao.getMax(checkDateTime);
    }

    @GetMapping("/avg{hours}")
    public List<Statistic> getStatisticAvg(@PathVariable Integer hours) {
        Instant checkDateTime = Instant.now().plus(-1 * hours, ChronoUnit.HOURS);
        return statisticDao.getAvg(checkDateTime);
    }


    @PostMapping
    public jTableEntityList<StatisticDTO> min(@PathVariable Integer hours) {
        Instant checkDateTime = Instant.now().plus(-1 * hours, ChronoUnit.HOURS);

        List<Statistic> target = statisticDao.getMin(checkDateTime);
        List<StatisticDTO> dtoList = mapper.map(target, new TypeToken<List<StatisticDTO>>() {}.getType());

        return new jTableEntityList<>("OK", dtoList, dtoList.size());
    }
}
