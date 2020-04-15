package org.pilipchuk.diblom.dao;

import org.pilipchuk.diblom.entities.SpecDaySetup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface SpecDaySetupDao extends CrudRepository<SpecDaySetup, Integer> {

    Page<SpecDaySetup> findAll(Pageable pageable);
}
