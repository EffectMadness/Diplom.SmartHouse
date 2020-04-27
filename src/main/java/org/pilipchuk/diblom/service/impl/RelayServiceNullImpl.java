package org.pilipchuk.diblom.service.impl;

import org.pilipchuk.diblom.dao.SpecDaySetupDao;
import org.pilipchuk.diblom.entities.Boiler;
import org.pilipchuk.diblom.entities.Sensor;
import org.pilipchuk.diblom.entity.OperationType;
import org.pilipchuk.diblom.service.BoilerService;
import org.pilipchuk.diblom.service.RelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class RelayServiceNullImpl  implements RelayService {

    private int gpio = 0;

    private int status;

    @Autowired
    private BoilerService boilerService;

    @Autowired
    private SpecDaySetupDao specDaySetupDao;

    @Autowired
    private Boiler boiler;

    public int getStatus() {
        return this.status;
    }


    public RelayServiceNullImpl(Boiler boiler) {
        this.status = boiler.getCurrentStatus();
        this.boiler = boiler;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public void relayOn() {
        if (boiler.getCurrentStatus() != 1) {
            System.out.println("Relay ON+++++");
        }
    }

    @Override
    public void relayOff() {
        if (boiler.getCurrentStatus() != 0)
        System.out.println("Relay OFF-----");
    }

    public void boilerOnOff(){
        if (boiler.getStatus() == 1) {
            Sensor controllSensor = boiler.getSensor();
            double maxTemperature;
            if (boiler.getOnLine() == 1){
                maxTemperature = boiler.getOnLineTemp();
            } else {
                maxTemperature = 0;//!!!!!!!!!!!
            };

        } else {
            relayOff();
            setStatus(boiler.getStatus());
        }
    }
}
