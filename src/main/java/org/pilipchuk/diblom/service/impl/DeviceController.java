package org.pilipchuk.diblom.service.impl;


import lombok.AllArgsConstructor;
import org.pilipchuk.diblom.dao.SensorDao;
import org.pilipchuk.diblom.entities.Sensor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;


@RestController
@AllArgsConstructor
public class DeviceController {
    private final SensorService sensorService;

    private final SensorDao sensorDao;

    @GetMapping("/sensors/sync")
    public List<Sensor> syncData() {
        return sensorService.syncSensors();
    }

    @GetMapping("/sensors")
    public Iterable<Sensor> getAll() {
        return sensorDao.findAll();
    }

    @GetMapping("/sensors/{id}")
    public Sensor getOne(@PathVariable Integer id) {
        return sensorDao.findById(id).get();
    }


}
