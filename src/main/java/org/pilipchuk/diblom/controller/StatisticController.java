package org.pilipchuk.diblom.controller;

import org.pilipchuk.diblom.dao.SensorDao;
import org.pilipchuk.diblom.dao.StatisticDao;

import org.pilipchuk.diblom.dto.StatisticDTO;
import org.pilipchuk.diblom.dto.jtable.jTableEntityList;
import org.pilipchuk.diblom.entities.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private StatisticDao statisticDao;

    @Autowired
    private SensorDao sensorDao;

    @PostMapping("/max")
    public jTableEntityList<StatisticDTO> max(@RequestParam(required = false) Integer hours) {
        if(hours == null) {
            hours = 24;
        }
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Instant checkDateTime = Instant.now().minus(hours, ChronoUnit.HOURS);
        Iterable<Temperature> lastData = statisticDao.getLastData(checkDateTime);
        List<StatisticDTO> dtoList = new ArrayList<>();
        sensorDao.findAll().forEach(sensor -> {
            final Instant[] tmpDateTime = {checkDateTime};
            final double[] tmpTemperature = {statisticDao.getLastSensorTemperature(sensor.getSensorId(), checkDateTime).iterator().next().getTemperature()};
            lastData.forEach(temperature -> {
                if (temperature.getSensor().getSensorId() == sensor.getSensorId()
                     && tmpTemperature[0] < temperature.getTemperature()){
                    tmpDateTime[0] = temperature.getDataTime();
                    tmpTemperature[0] = temperature.getTemperature();
                }
            });
            dtoList.add(new StatisticDTO(sensor.getSensorId(), dateTimeFormatter.format(Date.from(tmpDateTime[0])), sensor.getSensorName(), tmpTemperature[0]));
        });
        return new jTableEntityList<>("OK", dtoList, dtoList.size());
    }

    @PostMapping("/min")
    public jTableEntityList<StatisticDTO> min(@RequestParam(required = false) Integer hours) {
        if(hours == null) {
            hours = 24;
        }
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Instant checkDateTime = Instant.now().plus(-1 * hours, ChronoUnit.HOURS);
        Iterable<Temperature> lastData = statisticDao.getLastData(checkDateTime);
        List<StatisticDTO> dtoList = new ArrayList<>();

        sensorDao.findAll().forEach(sensor -> {
            final Instant[] tmpDateTime = {checkDateTime};
            final double[] tmpTemperature = {statisticDao.getLastSensorTemperature(sensor.getSensorId(), checkDateTime).iterator().next().getTemperature()};
            lastData.forEach(temperature -> {
                if (temperature.getSensor().getSensorId() == sensor.getSensorId()
                        && tmpTemperature[0] > temperature.getTemperature()){
                    tmpDateTime[0] = temperature.getDataTime();
                    tmpTemperature[0] = temperature.getTemperature();
                }
            });
            dtoList.add(new StatisticDTO(sensor.getSensorId(), dateTimeFormatter.format(Date.from(tmpDateTime[0])), sensor.getSensorName(), tmpTemperature[0]));
        });

        return new jTableEntityList<>("OK", dtoList, dtoList.size());
    }

}
