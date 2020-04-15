package org.pilipchuk.diblom.dao;

import org.pilipchuk.diblom.entities.Sensor;
import org.springframework.data.repository.CrudRepository;

public interface SensorDao extends CrudRepository<Sensor, Integer> {
}
