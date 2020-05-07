package org.pilipchuk.diblom.dao;

import org.pilipchuk.diblom.entities.Statistic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface StatisticDao  extends CrudRepository<Statistic, Integer> {

    @Query(value = "SELECT t.sensor.sensorId as sensorId, t.dataTime as dataTime, t.sensor.sensorName as sensorName, MIN(t.temperature) as temperature from Temperature t WHERE t.dataTime > :startDate GROUP BY t.sensor.sensorId")
    List<Statistic> getMin(@Param("startDate") Instant startDate);

    @Query(value = "SELECT t.sensor.sensorId as sensorId, t.dataTime as dataTime, t.sensor.sensorName as sensorName, MAX(t.temperature) as temperature from Temperature t WHERE t.dataTime > :startDate GROUP BY t.sensor.sensorId")
    List<Statistic> getMax(@Param("startDate") Instant startDate);

    @Query(value = "SELECT t.sensor.sensorId as sensorId, t.sensor.sensorName as sensorName, AVG(t.temperature) as temperature from Temperature t WHERE t.dataTime > :startDate GROUP BY t.sensor.sensorId")
    List<Statistic> getAvg(@Param("startDate") Instant startDate);

}
