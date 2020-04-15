package org.pilipchuk.diblom.service;

import org.pilipchuk.diblom.entities.Boiler;

public interface BoilerService {
    Boiler findFirst();
    void update(Boiler boiler);
}
