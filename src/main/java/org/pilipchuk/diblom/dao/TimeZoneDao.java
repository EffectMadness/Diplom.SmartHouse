package org.pilipchuk.diblom.dao;

import org.pilipchuk.diblom.entities.TimeZone;
import org.springframework.data.repository.CrudRepository;

public interface TimeZoneDao extends CrudRepository<TimeZone, String> {
}
