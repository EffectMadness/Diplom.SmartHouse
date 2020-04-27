package org.pilipchuk.diblom.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.pilipchuk.diblom.dao.SensorDao;
import org.pilipchuk.diblom.dto.SensorDTO;
import org.pilipchuk.diblom.dto.jtable.JTableEntity;
import org.pilipchuk.diblom.dto.jtable.JTableRequest;
import org.pilipchuk.diblom.dto.jtable.jTableEntityList;
import org.pilipchuk.diblom.entities.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.pilipchuk.diblom.utils.JTableUtil.toResponse;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private SensorDao sensorDao;
    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public jTableEntityList<SensorDTO> all() {
        List<Sensor> target = new ArrayList<>();
        sensorDao.findAll().forEach(target::add);
        List<SensorDTO> dtoList = mapper.map(target, new TypeToken<List<SensorDTO>>() {}.getType());
        return new jTableEntityList<>("OK", dtoList, dtoList.size());
    }

    @PostMapping("/create")
    public JTableEntity create(@ModelAttribute SensorDTO dto) {
        return toResponse(() -> {
            Sensor sensor = mapper.map(dto, Sensor.class);
            mapper.map(sensorDao.save(sensor), dto);
            return dto;
        });
    }

    @PostMapping("/update")
    public JTableEntity update(@ModelAttribute SensorDTO dto) {
        return toResponse(() -> {
            Sensor sensor = sensorDao.findById(dto.getSensorId()).orElseThrow(IllegalArgumentException::new);
            mapper.map(dto, sensor);
            sensorDao.save(sensor);

        });
    }

    @PostMapping("/delete")
    public JTableEntity delete(@RequestParam Integer sensorId) {
        return toResponse(() -> sensorDao.deleteById(sensorId));
    }
}
