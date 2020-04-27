package org.pilipchuk.diblom.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pilipchuk.diblom.dao.SensorDao;
import org.pilipchuk.diblom.dao.TemperatureDao;
import org.pilipchuk.diblom.entities.Sensor;
import org.pilipchuk.diblom.entities.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class SensorService {

    private final DeviceAdapter deviceAdapter;
    private final SensorDao sensorDao;
    private final TemperatureDao temperatureDao;

    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void scheduledSync() {
        syncSensors();
    }


    @Transactional
    public List<Sensor> syncSensors() {
        log.info("Start sensors sync ...");
        Map<String, Double> deviceData = deviceAdapter.readDeviceData();

        if (deviceData.isEmpty()) return Collections.emptyList();

        List<Sensor> sensors = sensorDao.findBySensorUids(deviceData.keySet());
        sensors.forEach(sensor -> {
            double actualTemp = deviceData.get(sensor.getSensorUid());
            if (actualTemp != sensor.getTemperatureValue()) {
                sensor.setTemperatureValue(actualTemp);
                temperatureDao.save(new Temperature(sensor, actualTemp));
            }
        });

        return sensors;
    }
}
