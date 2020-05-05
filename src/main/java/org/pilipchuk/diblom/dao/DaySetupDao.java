package org.pilipchuk.diblom.dao;

import org.pilipchuk.diblom.entities.DaySetup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DaySetupDao extends CrudRepository<DaySetup, Integer> {

    Page<DaySetup> findAll(Pageable pageable);

    @Query(value = "select t.temperature from day_setup d, time_zone t where d.time_zone = t.name and t.active = 1 and d.day= :weekDay and strftime('%H:%M',:curTime) between strftime('%H:%M', substr('00'||t.start_hour,-2, 2)||':'||substr('00'||t.start_min, -2, 2)) and strftime('%H:%M', substr('00'||t.end_hour,-2, 2)||':'||substr('00'||t.end_min, -2, 2))", nativeQuery = true)
    Optional<Integer> getTemperature(@Param("weekDay") String weekDay, @Param("curTime") String curTime);

}
