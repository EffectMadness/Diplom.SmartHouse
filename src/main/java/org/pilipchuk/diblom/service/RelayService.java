package org.pilipchuk.diblom.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public interface RelayService {

    int getStatus();

    void setStatus(int status);

    void relayOn();

    void relayOff();
}