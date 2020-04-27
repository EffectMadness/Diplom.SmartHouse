package org.pilipchuk.diblom.service.impl;

import com.pi4j.io.gpio.*;
import org.pilipchuk.diblom.entities.Boiler;
import org.pilipchuk.diblom.service.BoilerService;
import org.pilipchuk.diblom.service.RelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

public class RelayServiceImpl implements RelayService {

   @Value("${relay.GPIO}")
   private String gpio;

   private int status;

   @Autowired
   private BoilerService boilerService;

   final GpioController gpioController = GpioFactory.getInstance();

   @Autowired
   private Boiler boiler;

   public RelayServiceImpl(Boiler boiler) {
        this.status = boiler.getStatus();
        this.boiler = boiler;
   }

   public int getStatus() {
            return this.status;
        }

   @Transactional
   public void setStatus(int status) {
       this.status = status;
   }

   public void relayOn(){
       GpioPinDigitalOutput Relay = gpioController.provisionDigitalOutputPin(
               RaspiPin.GPIO_27,
               "Relay",
               PinState.HIGH);
   }

    public void relayOff(){
        GpioPinDigitalOutput Relay = gpioController.provisionDigitalOutputPin(
                RaspiPin.GPIO_27,
                "Relay",
                PinState.LOW);
    }

}
