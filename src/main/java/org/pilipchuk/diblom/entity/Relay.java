package org.pilipchuk.diblom.entity;

import org.pilipchuk.diblom.entities.Boiler;
import org.pilipchuk.diblom.service.BoilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

public class Relay {

    @Value("${relay.GPIO}")
    private int gpio;

    private int status;

    @Autowired
    private BoilerService boilerService;

    @Autowired
    private Boiler boiler;

    public Relay(Boiler boiler) {
        this.status = boiler.getStatus();
        this.boiler = boiler;
    }

    public int getStatus() {
        return this.status;
    }

    @Transactional
    public void setStatus(int status) {
        this.status = status;
        boiler.setStatus(status);
        boilerService.update(boiler);
    }
}
