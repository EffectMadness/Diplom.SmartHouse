package org.pilipchuk.diblom.controller;

import org.pilipchuk.diblom.dao.SensorDao;
import org.pilipchuk.diblom.dao.StatisticDao;
import org.pilipchuk.diblom.dto.GraphDataDTO;
import org.pilipchuk.diblom.entities.GraphData;
import org.pilipchuk.diblom.entities.Sensor;
import org.pilipchuk.diblom.entities.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;


@RestController
@RequestMapping("/graph")
public class GraphController {

    @Autowired
    StatisticDao statisticDao;

    @Autowired
    SensorDao sensorDao;


    @GetMapping("/graphData")
    public GraphData getGraphData(@RequestParam(required = false) Integer hours) {
        GraphData graphData = new GraphData();


        if (hours == null) {
            hours = 24;
        }
        //hours = 150;
        Instant curDateTime = Instant.now();
        Instant startDateTime = curDateTime.minus(hours, ChronoUnit.HOURS);
        Instant tmpDateTime = curDateTime.minus(hours, ChronoUnit.HOURS);
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        ArrayList<Instant> labelsTime = new ArrayList<>();
        while (curDateTime.isAfter(tmpDateTime) || curDateTime.equals(tmpDateTime)) {
            labelsTime.add(tmpDateTime);
            graphData.addLabel(dateTimeFormatter.format(Date.from(tmpDateTime)));
            tmpDateTime = tmpDateTime.plusSeconds(hours/6 * 5 * 60);
        }

        Iterable<Temperature> temperatures = statisticDao.getLastData(startDateTime);
        Iterable<Sensor> sensors = sensorDao.findAll();

        sensors.forEach(sensor -> {
                    GraphDataDTO dto = new GraphDataDTO(sensor.getSensorName(),
                            "rgb(0, 0, 100)",
                            getChartColor(sensor.getSensorId()),
                            false);

                    labelsTime.forEach(labelTime -> {
                        //dto.addData(getSensorTemperature(sensor.getSensorId(), labelTime, temperatures));
                        dto.addData(statisticDao.getLastSensorTemperature(sensor.getSensorId(), labelTime).iterator().next().getTemperature());
                    });
                    graphData.addData(sensor.getSensorId(), dto);
                }
        );
        return graphData;
    }

    private Double getSensorTemperature(Integer sensorId, Instant checkTime, Iterable<Temperature> temperatures) {
        final Double[] result = {Double.MIN_VALUE};
        for (Temperature temperature : temperatures) {
            if (temperature.getSensor().getSensorId() == sensorId) {
                if (temperature.getDataTime().isAfter(checkTime) && !result[0].equals(Double.MIN_VALUE)) {
                    continue;
                } else {
                    result[0] = temperature.getTemperature();
                }
            }
        }
        return result[0];
    }

    private String getChartColor(Integer sensorId) {
        String result = new String();
        switch (sensorId) {
            case (0):
                result = "rgb(255, 193, 7)";
                break;
            case (1):
                result = "rgb(40, 167, 69)";
                break;
            case (2):
                result = "rgb(23, 162, 184)";
                break;
            default:
                result = "rgb(0, 123, 255)";
                break;
        }
        return result;
    }

}
