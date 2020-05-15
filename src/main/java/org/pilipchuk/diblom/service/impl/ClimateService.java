package org.pilipchuk.diblom.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pilipchuk.diblom.dao.BoilerDao;
import org.pilipchuk.diblom.dao.SensorDao;
import org.pilipchuk.diblom.dao.TemperatureDao;
import org.pilipchuk.diblom.entities.Sensor;
import org.pilipchuk.diblom.entities.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class ClimateService {

    @Autowired
    BoilerDao boilerDao;

    private final DeviceAdapter deviceAdapter;
    private final SensorDao sensorDao;
    private final TemperatureDao temperatureDao;
    private final RelayService relayService;

    //@Scheduled(cron = "*/1 * * * *")
    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void scheduledSync() {
        //RelayService relayService = new RelayService();

        syncSensors();
        relayService.boilerOnOff();
    }


    @Transactional
    public List<Sensor> syncSensors() {
        log.info("Start sensors sync ...");
        Map<String, Double> deviceData = deviceAdapter.readDeviceData();

        if (deviceData.isEmpty()) return Collections.emptyList();

        List<Sensor> sensors = sensorDao.findBySensorUids(deviceData.keySet());
        sensors.forEach(sensor -> {
            double actualTemp = deviceData.get(sensor.getSensorUid()) + sensor.getDelta();
            System.out.println("Sensor " + sensor.getSensorUid() +" Temperature = " + actualTemp);
            if (actualTemp != sensor.getTemperatureValue()) {
                sensor.setTemperatureValue(actualTemp);
                System.out.println("Date = " + LocalDate.now() + "Save to database.");
                temperatureDao.save(new Temperature(sensor, actualTemp));
            }
        });

        return sensors;
    }
}
