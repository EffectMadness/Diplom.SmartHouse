package org.pilipchuk.diblom.service.impl;

import com.pi4j.io.gpio.*;
import lombok.extern.slf4j.Slf4j;
import org.pilipchuk.diblom.dao.BoilerDao;
import org.pilipchuk.diblom.dao.DaySetupDao;
import org.pilipchuk.diblom.dao.SpecDaySetupDao;
import org.pilipchuk.diblom.dao.WorkLogDao;
import org.pilipchuk.diblom.entities.Boiler;
import org.pilipchuk.diblom.entities.Sensor;
import org.pilipchuk.diblom.entities.WorkLog;
import org.pilipchuk.diblom.entity.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class RelayService {

    @Value("${relay.GPIO}")
    private String gpio;

    private int status;

    @Autowired
    private SpecDaySetupDao specDaySetupDao;

    @Autowired
    private DaySetupDao daySetupDao;

    @Autowired
    private BoilerDao boilerDao;

    @Autowired
    private WorkLogDao workLogDao;

    public int getStatus() { return this.status;}


    public void setStatus(int status) {
        this.status = status;
    }

    @Transactional
    public void relayOn() {
        Boiler boiler = StreamSupport.stream(boilerDao.findAll().spliterator(), false)
                .findFirst().orElse(null);
        if (boiler.getCurrentStatus() != 1) {
            // create gpio controller
            try {
                final GpioController gpioController = GpioFactory.getInstance();
                GpioPinDigitalOutput Relay = gpioController.provisionDigitalOutputPin(
                        RaspiPin.GPIO_27,
                        "Relay",
                        PinState.HIGH);
                boiler.setCurrentStatus(1);
                boiler.setTimeCurStatus(Instant.now());
                boilerDao.save(boiler);
                workLogDao.save(new WorkLog(OperationType.ON));
                System.out.println("Relay ON+++++");
                gpioController.shutdown();
            } catch (UnsatisfiedLinkError e){
                System.out.println("It is not a Rasberry PI");
            }
        }
    }

    @Transactional
    public void relayOff() {
        Boiler boiler = StreamSupport.stream(boilerDao.findAll().spliterator(), false)
                .findFirst().orElse(null);
        if (boiler.getCurrentStatus() != 0) {
            try {
                final GpioController gpioController = GpioFactory.getInstance();
                GpioPinDigitalOutput Relay = gpioController.provisionDigitalOutputPin(
                        RaspiPin.GPIO_27,
                        "Relay",
                        PinState.LOW);
                boiler.setCurrentStatus(0);
                boiler.setTimeCurStatus(Instant.now());
                boilerDao.save(boiler);
                workLogDao.save(new WorkLog(OperationType.OFF));
                System.out.println("Relay OFF-----");
                gpioController.shutdown();
            } catch (UnsatisfiedLinkError e){
                System.out.println("It is not a Rasberry PI");
            }
        }
    }

    public void boilerOnOff(){

        Boiler boiler = StreamSupport.stream(boilerDao.findAll().spliterator(), false)
                .findFirst().orElse(null);

        if (StreamSupport.stream(boilerDao.findAll().spliterator(), false)
                .findFirst().orElse(null).getStatus() == 1) {
            Sensor controllSensor = StreamSupport.stream(boilerDao.findAll().spliterator(), false)
                    .findFirst().orElse(null).getSensor();
            Integer onLineTemperature = StreamSupport.stream(boilerDao.findAll().spliterator(), false)
                    .findFirst().orElse(null).getOnLineTemp();
            Integer maxTemperature;
            Optional<Integer> tmpTemperature;

            Integer deltaTime = boiler.getDeltaTime();
            Integer deltaTemperature = boiler.getDeltaTemp();

            if (boiler.getOnLine() == 1 && onLineTemperature != 0){
                maxTemperature = onLineTemperature;
            } else {
                //get max temperature for special day
                String curDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
                tmpTemperature = specDaySetupDao.getSpecTemperature(curDate);
                if (tmpTemperature.isPresent()) {
                    maxTemperature = tmpTemperature.orElse(22);
                } else {
                    SimpleDateFormat dayFormatter = new SimpleDateFormat("EEEE", Locale.ENGLISH);
                    SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

                    String curWeekDay = dayFormatter.format(new Date());
                    String curTime = timeFormatter.format(new Date());

                    tmpTemperature = daySetupDao.getTemperature(curWeekDay, curTime);
                    maxTemperature = tmpTemperature.orElse(22);
                }
            }

            Instant nextCheckTime = boiler.getTimeCurStatus().plus(deltaTime, ChronoUnit.MINUTES);
            if (nextCheckTime.isBefore(Instant.now())){
                if (controllSensor.getTemperatureValue() <= maxTemperature - deltaTemperature){
                    relayOn();
                }else{
                    relayOff();
                }
            }else{
                System.out.println("Not now.");
            }

        } else {
            relayOff();
            setStatus(boiler.getStatus());
        }
    }
}
