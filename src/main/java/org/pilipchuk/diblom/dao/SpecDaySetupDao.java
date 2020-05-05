package org.pilipchuk.diblom.dao;

import org.pilipchuk.diblom.entities.SpecDaySetup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SpecDaySetupDao extends CrudRepository<SpecDaySetup, Integer> {

    Page<SpecDaySetup> findAll(Pageable pageable);

    @Query(value = "select t.temperature from spec_day_setup d, time_zone t where d.time_zone = t.name and t.active = 1 and strftime('%d/%m/%Y',:cur_date) between strftime('%d/%m/%Y',substr('00'||d.day_from,-2,2)||'/'||substr('00'||d.month_from,-2,2)||'/'||d.year_from) and strftime('%d/%m/%Y',substr('00'||d.day_from,-2,2)||'/'||substr('00'||d.month_from,-2,2)||'/'||d.year_from)", nativeQuery = true)
    Optional<Integer> getSpecTemperature(@Param("cur_date") String curDate);
}
