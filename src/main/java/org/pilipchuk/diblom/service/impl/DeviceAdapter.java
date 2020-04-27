package org.pilipchuk.diblom.service.impl;


import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.component.temperature.impl.TmpDS18B20DeviceType;
import com.pi4j.io.w1.W1Device;
import com.pi4j.io.w1.W1Master;
import org.pilipchuk.diblom.dao.SensorDao;
import org.pilipchuk.diblom.dao.TemperatureDao;
import org.pilipchuk.diblom.entities.Sensor;
import org.pilipchuk.diblom.entities.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class DeviceAdapter {
    @Value("${w1Sensor.path}")
    String w1Path;

    public Map<String, Double> readDeviceData(){
        W1Master master = new W1Master(w1Path);
        master.getDevices(TmpDS18B20DeviceType.FAMILY_CODE);

        Map<String, Double>  result = new HashMap<>();

        List<W1Device> w1Devices = master.getDevices(TmpDS18B20DeviceType.FAMILY_CODE);

        for (W1Device device : w1Devices) {
            //this line is enought if you want to read the temperature
            String id = device.getId().substring(0, 15);
            double temperature = ((TemperatureSensor) device).getTemperature() ;
            result.put(id, temperature);
        }

        return  result;
    }





}
