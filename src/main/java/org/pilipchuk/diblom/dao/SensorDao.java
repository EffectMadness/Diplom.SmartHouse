package org.pilipchuk.diblom.dao;

import org.pilipchuk.diblom.entities.Sensor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface SensorDao extends CrudRepository<Sensor, Integer> {
    Sensor findBySensorUid(String sensorUid);

    @Query(value = "SELECT s FROM Sensor s WHERE s.sensorUid IN :uids")
    List<Sensor> findBySensorUids(Collection<String> uids);
}
