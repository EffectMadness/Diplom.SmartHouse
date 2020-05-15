package org.pilipchuk.diblom.dao;

import org.pilipchuk.diblom.entities.Statistic;
import org.pilipchuk.diblom.entities.Temperature;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface StatisticDao  extends CrudRepository<Statistic, Integer> {

    @Query(value = "SELECT t from Temperature t WHERE t.dataTime > :startDate ORDER BY t.dataTime, t.sensor.sensorId")
    Iterable<Temperature> getLastData(@Param("startDate") Instant startDate);

//    @Query(value = "SELECT t from Temperature t WHERE t.dataTime > :startDate GROUP BY t.sensor HAVING MIN(t.temperature) = t.temperature")
//    Iterable<Temperature> getMin(@Param("startDate") Instant startDate);

//    @Query(value = "SELECT t from Temperature t WHERE t.dataTime > :startDate GROUP BY t.sensor HAVING MAX(t.temperature) = t.temperature")
//    Iterable<Temperature> getMax(@Param("startDate") Instant startDate);

    @Query(value = "SELECT t from Temperature t WHERE t.dataTime <= :startData and t.sensor.sensorId = :sensorId ORDER BY t.dataId DESC")
    Iterable<Temperature> getLastSensorTemperature(Integer sensorId, Instant startData);
}
