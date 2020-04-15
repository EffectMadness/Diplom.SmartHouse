package org.pilipchuk.diblom.dao;

import org.pilipchuk.diblom.entities.DaySetup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DaySetupDao extends CrudRepository<DaySetup, Integer> {

    Page<DaySetup> findAll(Pageable pageable);
}
